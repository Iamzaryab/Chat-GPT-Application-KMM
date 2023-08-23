//
//  MessageTo.swift
//  iosChatApp
//
//  Created by Zaryab on 27/01/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct MessageTo: View {
    
    private let message: String
    
    init(message: String) {
        self.message = message
    }
    
    var body: some View {
        HStack{
            Spacer(minLength: 50)
            PoppinsText(text: message, size: 10)
                .padding(EdgeInsets(
                    top: 8,
                    leading: 10,
                    bottom:8,
                    trailing: 8))
                .background(Color.theme.messageMe)
                .foregroundColor(Color.theme.textDescription)
                .cornerRadius(8)
            
        }
        
            
    }
}
