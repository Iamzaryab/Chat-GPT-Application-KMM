//
//  MessageView.swift
//  iosChatApp
//
//  Created by Zaryab on 25/01/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared
struct ChatView: View {
    @State private var messages: [ChatMessage] = []
    @State private var newMessage = ""
    @State private var messageCount = 0
    @State private var lastIncomingMessage = ""
    @State private var animationIndex = 0

    var body: some View {
        VStack {
            List {
                ForEach(messages) { message in
                    HStack {
                        if message.isFromUser {
                            Spacer()
                            Text(message.text)
                                .padding(.all, 10)
                                .background(Color.blue)
                                .foregroundColor(.white)
                                .cornerRadius(10)
                        } else {
                            if self.animationIndex == self.messages.count - 1 {
                                Text(self.lastIncomingMessage)
                                    .padding(.all, 10)
                                    .background(Color.gray)
                                    .foregroundColor(.white)
                                    .cornerRadius(10)
                                    .transition(.slide)
                                    .animation(Animation.easeInOut(duration: 1).repeatForever(autoreverses: false))
                            } else {
                                Text(message.text)
                                    .padding(.all, 10)
                                    .background(Color.gray)
                                    .foregroundColor(.white)
                                    .cornerRadius(10)
                            }
                            Spacer()
                        }
                    }
                }
            }

            HStack {
                            TextField("Enter your message", text: $newMessage)
                            .padding(.leading, 20)
                            Button(action: {
                                self.lastIncomingMessage = self.newMessage
                                self.messages.append(ChatMessage(text: self.newMessage, isFromUser: messageCount % 2 == 0))
                                self.newMessage = ""
                                self.messageCount += 1
                                self.animationIndex = self.messages.count - 1
                            }) {
                                Text("Send")
                            }.padding(.leading, 20)
                        }
                        .padding(.bottom, 20)

        }
    }
}

struct ChatMessage: Identifiable {
    let id = UUID()
    let text: String
    let isFromUser: Bool
}

//extension ChatView {
//
//enum chatResponse {
//        case loading
//        case result([Chat])
//        case error(String)
//    }
//
//    class ViewModel: ObservableObject {
//        let chatService: ChatService
//        @Published var result = chatResponse.loading
//
//        init(chatService: ChatService) {
//            self.chatService = chatService
//        }
//        func sendMessage(input:Input){
//        }
//        func loadLaunches(forceReload: Bool) {
//            self.result = chatResponse.loading
//            sdk.getLaunches(forceReload: forceReload, completionHandler: { launches, error in
//                if let launches = launches {
//                    self.launches = .result(launches)
//                } else {
//                    self.launches = .error(error?.localizedDescription ?? "error")
//                }
//            })
//        }
//    }
//}
//
//extension RocketLaunch: Identifiable { }
