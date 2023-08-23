//
//  Template.swift
//  iosChatApp
//
//  Created by Zaryab on 27/01/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct TemplateCard: View {
    private let title: String
    private let description:String
    
    init(title: String, description: String) {
        self.title = title
        self.description = description
    }
    var body: some View {
        RoundedRectangle(cornerRadius: 20)
            .padding(.horizontal, 10)
            .frame(minWidth: 0,maxWidth: .infinity,minHeight: 100,maxHeight: 100, alignment: .leading)
            .foregroundColor(Color.theme.card)
            .shadow(radius: 5)
            .overlay(
                VStack(alignment: .leading, spacing: 10 ){
                    PoppinsText(text: title, size: 14)
                        .foregroundColor(Color.theme.textTitle)
                    PoppinsText(text: description, size: 10)
                        .multilineTextAlignment(.leading)
                        .foregroundColor(Color.theme.textDescription)
                        
                    
                }
                    .padding()
                    .frame(maxWidth: .infinity, alignment: .leading)
            )
        
    }
}
