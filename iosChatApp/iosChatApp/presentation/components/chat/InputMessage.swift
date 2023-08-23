//
//  InputMessage.swift
//  iosChatApp
//
//  Created by Zaryab on 29/01/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct InputMessage: View {
    
    @State var query: String
    private let hint: String
    private let btnActive: Bool
    let onTriggerEvent: (ChatEvents) -> Void
 
    
    init(query: String, hint: String, btnActive: Bool, onTriggerEvent: @escaping (ChatEvents) -> Void) {
        self.query = query
        self.hint = hint
        self.btnActive = btnActive
        self.onTriggerEvent = onTriggerEvent
    }
    var body: some View {
        HStack{
            TextField(hint, text: $query)
                .font(Font.custom("poppins", size: 14))
                .multilineTextAlignment(.center)
                .padding()
                .keyboardType(.default)
                .disabled(btnActive)
                .background(RoundedRectangle(cornerRadius: 12).fill(Color.theme.messageMe))
                .padding(EdgeInsets(top: 4, leading: 10, bottom: 4, trailing: 0))
            Button(action: {
                onTriggerEvent(ChatEvents.GotMessage(message: query, from: 0))
                query = ""
            }){
                Image(!btnActive ? "send_btn_active" : "send_btn_inactive" )
                    .resizable()
                    .frame(minWidth: 40,maxWidth: 40,minHeight: 40, maxHeight: 40)
                    .padding(EdgeInsets(top: 0, leading: 0, bottom: 0, trailing: 8))
            }.disabled(btnActive)
            
        }
        .background(Color.theme.background.opacity(1))
    }
}
