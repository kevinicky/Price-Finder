/* Copyright 2017 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/

package com.example.android.pricefinder;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.example.android.pricefinder.model.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

/** Main {@code Activity} class for the Camera app. */
public class CameraActivity extends Activity {

    private ArrayList<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        //firebase database
        productList = new ArrayList<>();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference productRef = rootRef.child("product");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    Product product = new Product();
                    product.setItemID(ds.child("id").getValue(Integer.class));
                    product.setItemName(ds.child("name").getValue(String.class));
                    product.setItemDescription(ds.child("description").getValue(String.class));
                    product.setItemPrice(ds.child("price").getValue(Integer.class));
                    productList.add(product);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        productRef.addListenerForSingleValueEvent(valueEventListener);

        ProductPassing passing = ProductPassing.getInstance();
        passing.setPassingProductsList(productList);


        if (null == savedInstanceState) {
            getFragmentManager().beginTransaction().replace(R.id.container, Camera2BasicFragment.newInstance()).commit();

        }
    }


}
