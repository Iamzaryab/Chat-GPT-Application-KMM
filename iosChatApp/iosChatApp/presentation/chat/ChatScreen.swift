//
//  ChatScreen.swift
//  iosChatApp
//
//  Created by Zaryab on 27/01/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared
struct ChatScreen: View {
    
    private let question: String
    private let networkModule: NetworkModule
    
    @ObservedObject var viewModel: ChatViewModel
    init(question: String) {
        self.question = question
        networkModule = NetworkModule()
        self.viewModel = ChatViewModel(queryChat: QueryChat(chatService: networkModule.chatService))
        viewModel.onTriggerEvent(event: ChatEvents.GotMessage(message: question, from: 1))
        UIScrollView.appearance().keyboardDismissMode = .onDrag
    }
    var body: some View {
        ZStack {
            Color.theme.background.ignoresSafeArea()
            VStack{
                ScrollView(.vertical,showsIndicators: false){
                    VStack(spacing: 8){
                        ForEach(viewModel.chatState.messages,id: \.self){
                            message in
                            if(message.from == 0){
                                MessageTo(message: message.message)
                            }else{
                                MessageFrom(message: message.message,shouldAnimate: true)
                            }
                        }
                    }.padding(EdgeInsets(top: 0, leading: 10, bottom: 0, trailing: 10))
                }
                Spacer()
                
                InputMessage(
                    query: viewModel.chatState.query,
                    hint: viewModel.chatState.strHint,
                    btnActive: viewModel.chatState.btnActive,
                    onTriggerEvent: { event in
                        viewModel.onTriggerEvent(event: event)}).background(Color.clear)
                
            }
            
            
        }
    }
}


