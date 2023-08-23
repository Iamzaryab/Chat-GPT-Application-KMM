//
//  TypingBubble.swift
//  iosChatApp
//
//  Created by Zaryab on 30/01/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct TypingBubble: View {
    @State var dotIndex = 0
    
    var body: some View {
        HStack {
            if dotIndex == 0 {
                Circle()
                    .frame(width: 10, height: 10)
                    .foregroundColor(.gray)
            } else if dotIndex == 1 {
                HStack {
                    Circle()
                        .frame(width: 10, height: 10)
                        .foregroundColor(.gray)
                    Circle()
                        .frame(width: 10, height: 10)
                        .foregroundColor(.gray)
                        .opacity(0.5)
                }
            } else {
                HStack {
                    Circle()
                        .frame(width: 10, height: 10)
                        .foregroundColor(.gray)
                    Circle()
                        .frame(width: 10, height: 10)
                        .foregroundColor(.gray)
                        .opacity(0.5)
                    Circle()
                        .frame(width: 10, height: 10)
                        .foregroundColor(.gray)
                        .opacity(0.25)
                }
            }
        }
        .animation(Animation.linear(duration: 0.5).repeatForever(autoreverses: false))
        .onAppear {
            self.dotIndex = 0
            Timer.scheduledTimer(withTimeInterval: 0.5, repeats: true) { timer in
                self.dotIndex = (self.dotIndex + 1) % 3
            }
        }
    }
}
