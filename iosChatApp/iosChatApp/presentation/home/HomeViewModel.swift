//
//  HomeViewModel.swift
//  iosChatApp
//
//  Created by Zaryab on 27/01/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared
import FirebaseRemoteConfig

class HomeViewModel:ObservableObject{
    
    private let logger = Logger(className: "HomeViewModel")
    
    
    //Dependencies
    let loadData: LoadData
    let changeCategory: ChangeCategory
    
    
    private let remoteConfig = RemoteConfig.remoteConfig()
    private var jobs: [Kotlinx_coroutines_coreJob] = []
//    var categories:[shared.Category]
//    var templates:[shared.Template]
    
    @Published var state: HomeState = HomeState()
    
    init(loadData: LoadData, changeCategory: ChangeCategory) {
        self.loadData = loadData
        self.changeCategory = changeCategory
        let settings = RemoteConfigSettings()
        settings.minimumFetchInterval = 0
        remoteConfig.configSettings = settings
//        categories=[]
//        templates=[]
        fetchConfig()
        
    }
    
    deinit {
        jobs.forEach { $0.cancel(cause: nil) }
    }
    
    func fetchConfig() {
        self.remoteConfig.fetch() { [weak self] (status, error) -> Void in
            guard let self = self else { return }
            if status == .success {
                self.remoteConfig.activate()
                let strTemplates = self.remoteConfig.configValue(forKey: "templates").stringValue ?? ""
                let strCategories = self.remoteConfig.configValue(forKey: "category").stringValue ?? ""
                let strConfig = self.remoteConfig.configValue(forKey: "config").stringValue ?? ""
                self.onTriggerEvent(event: HomeEvents.LoadData(templates: strTemplates, categories: strCategories, config: strConfig))
            } else {
                // Stop the shimmer instead of loading forever.
                self.updateState(isLoading: false)
                self.logger.log(msg: "Config not fetched: \(error?.localizedDescription ?? "No error available.")")
            }
            
        }
    }
    
    func onTriggerEvent(event: HomeEvents) {
        switch event{
        case is HomeEvents.OnSelectedCategory:
            onSeletedCategory(category: (event as! HomeEvents.OnSelectedCategory).category)
        case is HomeEvents.LoadData:
            loadData(
                strTemplates: (event as! HomeEvents.LoadData).templates,
                strCategories: (event as! HomeEvents.LoadData).categories,
                strConfig: (event as! HomeEvents.LoadData).config)
        default:
            doNothing()
        }
    }
    
    func updateState(
        isLoading: Bool? = nil ,
        filteredTemplates:[Template]? = nil,
        selectedCategory: shared.Category? = nil,
        categories: [shared.Category]? = nil,
        templates: [Template]? = nil,
        config: Config? = nil
    ){
        let currentState = (self.state.copy() as! HomeState)
        self.state = self.state.doCopy(
            isLoading: isLoading ?? currentState.isLoading,
            filteredTemplates: filteredTemplates ?? currentState.filteredTemplates ,
            selectedCategory: selectedCategory ?? currentState.selectedCategory,
            categories: categories ?? currentState.categories,
            templates: templates ?? currentState.templates,
            config: config ?? currentState.config
        )
    }
    
    private func loadData(strTemplates: String,strCategories: String, strConfig: String){
        let job = loadData.execute(
            templates: strTemplates,
            categories: strCategories,
            config: strConfig).collectCommon(
                coroutineScope: nil,
                callback:{ [weak self] dataState in
                    guard let self = self, let dataState = dataState else { return }
                    self.updateState(isLoading: dataState.isLoading)
                    if(dataState.isLoading){ return }
                    
                    if let data = dataState.data {
                        self.setData(remoteConfigValues: data)
                    }else{
                        self.logger.log(msg: "Could not load templates: \(dataState.message ?? "unknown error")")
                    }
                }
            )
        jobs.append(job)
    }
    
    private func setData(remoteConfigValues: RemoteConfigValues){
        self.updateState(
            isLoading: false,
            filteredTemplates: remoteConfigValues.templates,
            selectedCategory: remoteConfigValues.categories.first,
            categories: remoteConfigValues.categories,
            templates: remoteConfigValues.templates,
            config: remoteConfigValues.config
        )
        
    }
    private func onSeletedCategory(category: shared.Category){
        self.updateState(selectedCategory: category)
        
        let job = changeCategory.execute(category: category, templates: self.state.templates)
            .collectCommon(coroutineScope: nil, callback:{ [weak self] datastate in
                guard let self = self, let datastate = datastate else { return }
                self.updateState(isLoading: datastate.isLoading)
                if let filtered = datastate.data as? [Template] {
                    self.updateState(filteredTemplates: filtered)
                } else if let error = datastate.message {
                    self.logger.log(msg: error)
                }
            })
        jobs.append(job)
    }
    private func doNothing(){
        
    }
    
}
