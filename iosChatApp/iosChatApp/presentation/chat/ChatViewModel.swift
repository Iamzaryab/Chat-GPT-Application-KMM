//
//  ChatViewModel.swift
//  iosChatApp
//
//  Created by Zaryab on 27/01/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import shared
class ChatViewModel: ObservableObject{
    
    private static let defaultHint = "Ask me Anything"
    
    private let logger = Logger(className: "ChatViewModel")
    
    let queryChat: QueryChat
    private let config: Config?
    private var queryJob: Kotlinx_coroutines_coreJob?
    
    @Published var chatState: ChatState = ChatState()
    
    init(queryChat: QueryChat, config: Config?) {
        self.queryChat = queryChat
        self.config = config
        updateState(btnActive: false)
    }
    
    deinit {
        queryJob?.cancel(cause: nil)
    }
    
    func onTriggerEvent(event: ChatEvents){
        switch event{
        case let gotMessage as ChatEvents.GotMessage:
            onGotMessage(message: gotMessage.message, from: Int(gotMessage.from))
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
    
    
    private func onGotMessage(message: String, from: Int){
        if(from == Int(Message.companion.FROM_ASSISTANT)){
            appendMessage(text: message, from: Message.companion.FROM_ASSISTANT)
        }else{
            let trimmed = message.trimmingCharacters(in: .whitespacesAndNewlines)
            if(trimmed.isEmpty){ return }
            appendMessage(text: trimmed, from: Message.companion.FROM_USER)
            sendQuery()
        }
    }
    
    private func appendMessage(text: String, from: Int32){
        var currentMessages = chatState.messages
        currentMessages.append(Message(index: Int32(currentMessages.count), from: from, message: text, createAt: 0))
        updateState(messages: currentMessages)
    }
    
    private func sendQuery(){
        updateState(btnActive: true, strhint: "I am thinking about your question", query: "")
        queryJob?.cancel(cause: nil)
        queryJob = queryChat.execute(
            history: chatState.messages,
            apiKey: config?.apiKey ?? "",
            model: config?.model
        ).collectCommon(
            coroutineScope: nil,
            callback: { [weak self] dataState in
                guard let self = self, let dataState = dataState else { return }
                self.updateState(isLoading: dataState.isLoading)
                if(dataState.isLoading){ return }
                
                if let reply = dataState.data {
                    self.appendMessage(text: reply.message, from: Message.companion.FROM_ASSISTANT)
                } else {
                    let error = dataState.message ?? "Unknown error"
                    self.logger.log(msg: error)
                    self.appendMessage(text: "Sorry, something went wrong: \(error)", from: Message.companion.FROM_ASSISTANT)
                }
                // Always unlock the input, whether the request worked or not.
                self.updateState(btnActive: false, strhint: ChatViewModel.defaultHint)
            })
    }
    
}
