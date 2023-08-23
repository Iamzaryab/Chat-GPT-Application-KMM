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
    
    func fetchConfig() {
        self.remoteConfig.fetch() { (status, error) -> Void in
            if status == .success {
                self.remoteConfig.activate()
                let strTemplates = self.remoteConfig.configValue(forKey: "templates").stringValue ?? ""
                let strCategories = self.remoteConfig.configValue(forKey: "category").stringValue ?? ""
                let strConfig = self.remoteConfig.configValue(forKey: "config").stringValue ?? ""
                self.onTriggerEvent(event: HomeEvents.LoadData(templates: strTemplates, categories: strCategories, config: strConfig))
            } else {
                print("Config not fetched")
                print("Error: \(error?.localizedDescription ?? "No error available.")")
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
        templates: [Template]? = nil
    ){
        let currentState = (self.state.copy() as! HomeState)
        self.state = self.state.doCopy(
            isLoading: isLoading ?? currentState.isLoading,
            filteredTemplates: filteredTemplates ?? currentState.filteredTemplates ,
            selectedCategory: selectedCategory ?? currentState.selectedCategory,
            categories: categories ?? currentState.categories,
            templates: templates ?? currentState.templates
        )
    }
    
    private func loadData(strTemplates: String,strCategories: String, strConfig: String){
        do{
            try loadData.execute(
                templates: strTemplates,
                categories: strCategories,
                config: strConfig).collectCommon(
                    coroutineScope: nil,
                    callback:{ dataState in
                        if(dataState != nil){
                            let data = dataState?.data
                            let message = dataState?.message
                            let loading = dataState?.isLoading
                            
                            self.updateState(isLoading: loading)
                            
                            if(data != nil){
                                
                                self.setData(remoteConfigValues: data as! RemoteConfigValues)
                            }else{
                                self.logger.log(msg: "Data is null")
                            }
                        }
                    }
                )
            
            
        }catch{
            self.logger.log(msg: "\(error)")
        }
    }
    
    private func setData(remoteConfigValues: RemoteConfigValues){
        self.updateState(
            isLoading: false,
            filteredTemplates: remoteConfigValues.templates,
            selectedCategory: remoteConfigValues.categories[0],
            categories: remoteConfigValues.categories,
            templates: remoteConfigValues.templates
        )
        
    }
    private func onSeletedCategory(category: shared.Category){
        self.updateState(selectedCategory: category)
        
        do{
            try changeCategory.execute(category: category, templates: self.state.templates)
                .collectCommon(coroutineScope: nil, callback:{
                    datastate in if( datastate != nil){
                        self.updateState(isLoading: datastate?.isLoading)
                        
                        if(datastate?.data != nil){                            
                            self.updateState(filteredTemplates: datastate?.data as! [Template])
                        }
                    }
                }
            )
            
        }catch{
            logger.log(msg: error as! String)
        }
    }
    private func doNothing(){
        
    }
    
}
