/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.navigation

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.android.navigation.databinding.FragmentGameBinding
import kotlinx.android.synthetic.main.fragment_game.*

class GameFragment : Fragment() {


    // The first answer is the correct one.  We randomize the answers before showing the text.
    // All questions must have four answers.  We'd want these to contain references to string
    // resources so we could internationalize. (Or better yet, don't define the questions in code...)
    private lateinit var binding: FragmentGameBinding


    var count = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentGameBinding>(
                inflater, R.layout.fragment_game, container, false)

        val myActivity = activity as MainActivity?
        binding.nameTextview.text = myActivity?.nameText


        // Bind this fragment class to the layout
        binding.game = this

        setListeners()

        return binding.root
    }

    private fun setListeners(){
        val twoDimensional: List<List<View>> = listOf(
                listOf(binding.box, binding.box1, binding.box2, binding.box3, binding.box4),
                listOf(binding.box5, binding.box6, binding.box7, binding.box8, binding.box9),
                listOf(binding.box10, binding.box11, binding.box12, binding.box13, binding.box14),
                listOf(binding.box15, binding.box16, binding.box17, binding.box18, binding.box19),
                listOf(binding.box20, binding.box21, binding.box22, binding.box23, binding.box24)
        )

        var indicator: Array<Array<Int>> = arrayOf<Array<Int>>()
        for (i in 0..4) {
            var array = arrayOf<Int>()
            for (j in 0..4) {
                array += 0
            }
            indicator += array
        }

        for(item: Int in (0..4)){
            for (item2: Int in(0..4)){
                twoDimensional[item][item2].setOnClickListener {flipLights(it,twoDimensional,item,item2,indicator)}
            }
        }

        binding.retryButton.setOnClickListener{retry(twoDimensional,indicator)}

    }
    private fun flipLights(view: View,twodimensional : List<List<View>>,row:Int,col:Int,indicator:Array<Array<Int>>){

        count++
        var text = "Count: "+ count
        binding.clickCount.setText(text)

        if(indicator[row][col] == 0) {
            view.setBackgroundColor(Color.BLACK)
            indicator[row][col] = 1
        }else{
            view.setBackgroundColor(Color.WHITE)
            indicator[row][col] = 0
        }

        if(row == 0) when (col){  //top
            0 -> { //left most
                if(indicator[row][col+1]==0) { //right white
                    twodimensional[row][col+1].setBackgroundColor(Color.BLACK)
                    indicator[row][col+1] = 1

                }else{ //right black
                    twodimensional[row][col+1].setBackgroundColor(Color.WHITE)
                    indicator[row][col+1] = 0
                }
                if(indicator[row+1][col]==0){ //down white
                    twodimensional[row+1][col].setBackgroundColor(Color.BLACK)
                    indicator[row+1][col] = 1
                }else{ //down black
                    twodimensional[row+1][col].setBackgroundColor(Color.WHITE)
                    indicator[row+1][col] = 0
                }
            }
            4 -> { //rightmost
                if(indicator[row][col-1]==0) { //left white
                    twodimensional[row][col-1].setBackgroundColor(Color.BLACK)
                    indicator[row][col-1] = 1
                }else{
                    twodimensional[row][col-1].setBackgroundColor(Color.WHITE)
                    indicator[row][col-1] = 0
                }
                if(indicator[row+1][col]==0){ //down white
                    twodimensional[row+1][col].setBackgroundColor(Color.BLACK)
                    indicator[row+1][col] = 1
                }else{
                    twodimensional[row+1][col].setBackgroundColor(Color.WHITE)
                    indicator[row+1][col] = 0
                }
            }
            else -> {
                if(indicator[row][col+1]==0) { //right white
                    twodimensional[row][col+1].setBackgroundColor(Color.BLACK)
                    indicator[row][col+1] = 1
                }else{
                    twodimensional[row][col+1].setBackgroundColor(Color.WHITE)
                    indicator[row][col+1] = 0
                }
                if(indicator[row][col-1]==0) { //left white
                    twodimensional[row][col-1].setBackgroundColor(Color.BLACK)
                    indicator[row][col-1] = 1
                }else{
                    twodimensional[row][col-1].setBackgroundColor(Color.WHITE)
                    indicator[row][col-1] = 0
                }
                if(indicator[row+1][col]==0){ //down white
                    twodimensional[row+1][col].setBackgroundColor(Color.BLACK)
                    indicator[row+1][col] = 1
                }else{
                    twodimensional[row+1][col].setBackgroundColor(Color.WHITE)
                    indicator[row+1][col] = 0
                }
            }
        }

        else if(row == 4) when (col){ //bottom
            0 -> { //left most
                if(indicator[row][col+1]==0) { //right white
                    twodimensional[row][col+1].setBackgroundColor(Color.BLACK)
                    indicator[row][col+1] = 1

                }else{ //right black
                    twodimensional[row][col+1].setBackgroundColor(Color.WHITE)
                    indicator[row][col+1] = 0
                }
                if(indicator[row-1][col]==0){ //up white
                    twodimensional[row+-1][col].setBackgroundColor(Color.BLACK)
                    indicator[row-1][col] = 1
                }else{ //up black
                    twodimensional[row-1][col].setBackgroundColor(Color.WHITE)
                    indicator[row-1][col] = 0
                }
            }
            4 -> { //rightmost
                if(indicator[row][col-1]==0) { //left white
                    twodimensional[row][col-1].setBackgroundColor(Color.BLACK)
                    indicator[row][col-1] = 1
                }else{
                    twodimensional[row][col-1].setBackgroundColor(Color.WHITE)
                    indicator[row][col-1] = 0
                }
                if(indicator[row-1][col]==0){ //up white
                    twodimensional[row-1][col].setBackgroundColor(Color.BLACK)
                    indicator[row-1][col] = 1
                }else{ //up black
                    twodimensional[row-1][col].setBackgroundColor(Color.WHITE)
                    indicator[row-1][col] = 0
                }
            }
            else -> {
                if(indicator[row][col+1]==0) { //right white
                    twodimensional[row][col+1].setBackgroundColor(Color.BLACK)
                    indicator[row][col+1] = 1
                }else{
                    twodimensional[row][col+1].setBackgroundColor(Color.WHITE)
                    indicator[row][col+1] = 0
                }
                if(indicator[row][col-1]==0) { //left white
                    twodimensional[row][col-1].setBackgroundColor(Color.BLACK)
                    indicator[row][col-1] = 1
                }else{
                    twodimensional[row][col-1].setBackgroundColor(Color.WHITE)
                    indicator[row][col-1] = 0
                }
                if(indicator[row-1][col]==0){ //up white
                    twodimensional[row-1][col].setBackgroundColor(Color.BLACK)
                    indicator[row-1][col] = 1
                }else{
                    twodimensional[row-1][col].setBackgroundColor(Color.WHITE)
                    indicator[row-1][col] = 0
                }
            }
        }

        if(col == 0) {  // left vertical
            if(row in 1..3) {
                if(indicator[row-1][col]==0){ //up white
                    twodimensional[row-1][col].setBackgroundColor(Color.BLACK)
                    indicator[row-1][col] = 1
                }else{ //up black
                    twodimensional[row-1][col].setBackgroundColor(Color.WHITE)
                    indicator[row-1][col] = 0
                }

                if(indicator[row+1][col]==0){ //down white
                    twodimensional[row+1][col].setBackgroundColor(Color.BLACK)
                    indicator[row+1][col] = 1
                }else{
                    twodimensional[row+1][col].setBackgroundColor(Color.WHITE)
                    indicator[row+1][col] = 0
                }

                if(indicator[row][col+1]==0) { //right white
                    twodimensional[row][col+1].setBackgroundColor(Color.BLACK)
                    indicator[row][col+1] = 1

                }else{ //right black
                    twodimensional[row][col+1].setBackgroundColor(Color.WHITE)
                    indicator[row][col+1] = 0
                }

            }
        }
        else if(col == 4){
            if(row in 1..3){
                if(indicator[row-1][col]==0){ //up white
                    twodimensional[row-1][col].setBackgroundColor(Color.BLACK)
                    indicator[row-1][col] = 1
                }else{ //up black
                    twodimensional[row-1][col].setBackgroundColor(Color.WHITE)
                    indicator[row-1][col] = 0
                }

                if(indicator[row+1][col]==0){ //down white
                    twodimensional[row+1][col].setBackgroundColor(Color.BLACK)
                    indicator[row+1][col] = 1
                }else{
                    twodimensional[row+1][col].setBackgroundColor(Color.WHITE)
                    indicator[row+1][col] = 0
                }

                if(indicator[row][col-1]==0) { //left white
                    twodimensional[row][col-1].setBackgroundColor(Color.BLACK)
                    indicator[row][col-1] = 1
                }else{
                    twodimensional[row][col-1].setBackgroundColor(Color.WHITE)
                    indicator[row][col-1] = 0
                }
            }
        }
        if(row in 1..3 && col in 1..3){ //middle
            if(indicator[row-1][col]==0){ //up white
                twodimensional[row-1][col].setBackgroundColor(Color.BLACK)
                indicator[row-1][col] = 1
            }else{ //up black
                twodimensional[row-1][col].setBackgroundColor(Color.WHITE)
                indicator[row-1][col] = 0
            }

            if(indicator[row+1][col]==0){ //down white
                twodimensional[row+1][col].setBackgroundColor(Color.BLACK)
                indicator[row+1][col] = 1
            }else{
                twodimensional[row+1][col].setBackgroundColor(Color.WHITE)
                indicator[row+1][col] = 0
            }

            if(indicator[row][col-1]==0) { //left white
                twodimensional[row][col-1].setBackgroundColor(Color.BLACK)
                indicator[row][col-1] = 1
            }else{
                twodimensional[row][col-1].setBackgroundColor(Color.WHITE)
                indicator[row][col-1] = 0
            }

            if(indicator[row][col+1]==0) { //right white
                twodimensional[row][col+1].setBackgroundColor(Color.BLACK)
                indicator[row][col+1] = 1

            }else{ //right black
                twodimensional[row][col+1].setBackgroundColor(Color.WHITE)
                indicator[row][col+1] = 0
            }
        }

        var black = 0


        for(i in 0..4) for(j in 0..4) if(indicator[i][j] == 1) black += 1

        if(black == 25) {
            val myActivity = activity as MainActivity?
            myActivity?.countText = count.toString()
            view.findNavController().navigate(R.id.action_gameFragment_to_gameWonFragment)
        }



    }

    private fun retry(twoDimensional : List<List<View>>,indicator:Array<Array<Int>>){

        for(item: Int in (0..4)){
            for (item2: Int in(0..4)){
                twoDimensional[item][item2].setBackgroundColor(Color.WHITE)
            }
        }

        for (i in 0..4) {
            for (j in 0..4) {
                indicator[i][j] = 0
            }
        }

        count = 0
        var text = "Clicks: " + count
        binding.clickCount.setText(text)

    }

}
