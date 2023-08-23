//
//  MessageFrom.swift
//  iosChatApp
//
//  Created by Zaryab on 27/01/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct MessageFrom: View {
    
    private let message:String
    private let shouldAnimate:Bool
    
    @State var text: String = ""
    
    init(message: String, shouldAnimate: Bool=true) {
        self.message = message
        self.shouldAnimate = shouldAnimate
        
//        typeWriter()
        //        if(self.shouldAnimate){
        //            typeWriter()
        //        }
        //        else{
        //            self.text = message
        //        }
    }
    
    var body: some View {
        HStack{
            PoppinsText(text: message, size: 10)
                .padding(EdgeInsets(
                    top: 8,
                    leading: 10,
                    bottom:8,
                    trailing: 8))
                .background(Color.theme.messageFrom)
                .foregroundColor(Color.theme.textDescription)
                .cornerRadius(8)
            Spacer(minLength: 50)
            
        }
        
    }
    
    func typeWriter(at position: Int = 0) {

        if position == 0 {
            text = ""
        }
        if position < message.count {
            DispatchQueue.main.asyncAfter(deadline: .now() + 0.1) {
                text.append(message[position])
                typeWriter(at: position + 1)
            }
        }
    }
    
    
}
extension String {
    subscript(offset: Int) -> Character {
        self[index(startIndex, offsetBy: offset)]
    }
}


