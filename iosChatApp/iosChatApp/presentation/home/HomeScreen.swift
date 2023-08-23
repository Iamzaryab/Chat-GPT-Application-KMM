//
//  HomeScreen.swift
//  iosChatApp
//
//  Created by Zaryab on 27/01/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared


struct HomeScreen: View {
    
    //dependencies
    private let remoteConfigModule: RemoteConfigModule
    private let loadDataModule: LoadDataModule
    private let changeCategoryModule: ChangeCategoryModule    
    @ObservedObject var viewModel: HomeViewModel
    
    
    init(remoteConfigModule: RemoteConfigModule) {
        self.remoteConfigModule = remoteConfigModule
        self.loadDataModule = LoadDataModule(getRemoteConfigParsed: remoteConfigModule.getRemoteConfigParsed)
        self.changeCategoryModule = ChangeCategoryModule()
        self.viewModel = HomeViewModel(
            loadData: self.loadDataModule.loadData,
            changeCategory: changeCategoryModule.changeCategory)
        self.viewModel.updateState(isLoading: true)
    }
    
    var body: some View {
        NavigationView(){
            ZStack {
                Color.theme.background.ignoresSafeArea()
                if(viewModel.state.isLoading){
                    HomeShimmers()
                }else{
                    VStack{
                        ScrollView(.horizontal, showsIndicators: false ){
                            VStack{
                                HStack(spacing: 10){
                                    ForEach(viewModel.state.categories,id: \.self ){
                                        category in CategoryChip(title:category.title.uppercased(), isSelected: viewModel.state.selectedCategory == category)
                                            .onTapGesture{
                                                
                                                viewModel.onTriggerEvent(event: HomeEvents.OnSelectedCategory(category: category))
                                                print(category)
                                            }
                                    }
                                }.padding(EdgeInsets(top: 10, leading: 8, bottom: 5, trailing: 8))
                            }
                            
                        }
                        ScrollView(.vertical ,showsIndicators: false){
                            VStack(spacing: 10){
                                ForEach(viewModel.state.filteredTemplates,id: \.self){
                                    template in
                                    NavigationLink(destination: ChatScreen(question: template.question)){
                                        TemplateCard(title: template.title, description: template.description_ )
                                    }.buttonStyle(PlainButtonStyle())
                                    
                                }
                            }.padding(EdgeInsets(top: 10, leading: 0, bottom: 8, trailing: 0))
                        }
                        
                        
                    }
                }
        
               
            } .navigationBarHidden(true)
        }
    }
        
}
