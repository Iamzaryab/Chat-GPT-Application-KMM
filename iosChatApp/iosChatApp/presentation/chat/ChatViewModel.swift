//
//  ChatViewModel.swift
//  iosChatApp
//
//  Created by Zaryab on 27/01/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import shared
class ChatViewModel: ObservableObject{
    
    private let logger = Logger(className: "ChatViewModel")
    
    let queryChat: QueryChat
    
    @Published var chatState: ChatState = ChatState()
    
    init(queryChat: QueryChat) {
        self.queryChat = queryChat
        updateState(btnActive: false)
    }
    
    func onTriggerEvent(event: ChatEvents){
        switch event{
        case is ChatEvents.GotMessage:
            gotMessage(
                message: (event as! ChatEvents.GotMessage).message,
                from: Int((event as! ChatEvents.GotMessage).from)
            )
        default :
            doNothing()
        }
    }
    func doNothing(){
        
    }
    
    func updateState(
        isLoading: Bool? = nil,
        messages: [Message]? = nil,
        lastMessage: Message? = nil,
        btnActive:Bool? = nil,
        strhint: String? = nil,
        query:String? = nil
        
    ){
        let currentState = (self.chatState.copy() as! ChatState)
        self.chatState = self.chatState.doCopy(
            isLoading: isLoading ?? currentState.isLoading,
            messages: messages ?? currentState.messages,
            lastMessage: lastMessage ?? currentState.lastMessage,
            btnActive: btnActive ?? currentState.btnActive,
            strHint: strhint ?? currentState.strHint,
            query: query ?? currentState.query
        )
        
    }
    
    
    private func gotMessage(message: String, from: Int){
        if(from == 1){
            let message: Message = Message(index: 0, from: 1, message: message, createAt: 0)
            updateMessages(message: message)
        }else{
            let input: Input = Input(model: "text-davinci-003", prompt: message, temperature: 0, maxTokens: 50)
            queryChat(input: input, apiKey: "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")
        }
        
    }
    private func updateMessages(message: Message){
        let currentState = (self.chatState.copy() as! ChatState)
        var currentMessages = currentState.messages
        currentMessages.append(message)
        updateState(messages: currentMessages)
    }
    private func queryChat(input: Input, apiKey: String){
        let message: Message = Message(index: 0, from: 0, message: input.prompt, createAt: 0)
        updateMessages(message: message)
        updateState(query: "")
        updateState(strhint: "I am thinking about your question")
        updateState(btnActive: true)
        do{
            try queryChat.execute(
                input: input,
                apiKey: apiKey
            ).collectCommon(
                coroutineScope: nil,
                callback: {
                    dataState in
                    if(dataState != nil){
                        let data = dataState?.data
                        let message = dataState?.message
                        let loading = dataState?.isLoading
                        self.updateState(isLoading: loading)
                        if(data != nil){
                            self.updateMessages(message: data as! Message)
                            self.updateState(strhint: "Ask me Anything")
                            self.updateState(btnActive: false)
                        }
                    }
                })
            
        }catch{
            logger.log(msg: "\(error)")
        }
    }
    
    
    
    
}
