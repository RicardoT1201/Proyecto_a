package com.example.proyecto_a.videoModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.proyecto_a.pojo.Meal
import com.example.proyecto_a.pojo.MealList
import com.example.proyecto_a.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealViewModel():ViewModel() {
    private var mealDetailsLiveData = MutableLiveData<Meal>()

    fun getMealDetail(id:String){
        RetrofitInstance.api.getMealDetails(id).enqueue(object : Callback<MealList>{
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if (response.body()!=null){
                    mealDetailsLiveData.value = response.body()!!.meals[0]
                }
                else
                    return
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.d("MealActivity",t.message.toString())

            }

        })
    }
    fun observerMealDetailsLiveData():LiveData<Meal>{
        return mealDetailsLiveData
    }

}