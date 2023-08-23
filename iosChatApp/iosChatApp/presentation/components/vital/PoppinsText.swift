//
//  PoppinsText.swift
//  iosChatApp
//
//  Created by Zaryab on 28/01/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct PoppinsText: View {
    private let text:String
    private let size:CGFloat
    
    init(text: String, size: CGFloat) {
        self.text = text
        self.size = size
    }
    var body: some View {
        Text(text)
            .font(
                Font.custom(
                    "poppins",
                    size: size))
    }
}

