//
//  HomeShimmers.swift
//  iosChatApp
//
//  Created by Zaryab on 30/01/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import Shimmer
struct HomeShimmers: View {
    var body: some View {
        ZStack {
            Color.theme.background.ignoresSafeArea()
            VStack{
                ScrollView(.horizontal, showsIndicators: false ){
                    VStack{
                        HStack(spacing: 10){
                            ForEach(1..<9){ index in
                                CategoryChipShimmer().modifier(Shimmer())
                                
                            }
                        }.padding(EdgeInsets(top: 10, leading: 8, bottom: 5, trailing: 8))
                    }
                    
                }
                ScrollView(.vertical ,showsIndicators: false){
                    VStack(spacing: 10){
                        ForEach(1..<9){ index in
                            TemplateShimmer().modifier(Shimmer())
                        }
                    }.padding(EdgeInsets(top: 10, leading: 0, bottom: 8, trailing: 0))
                }
                
                
            }
            .navigationBarHidden(true)
        }
    }
}
