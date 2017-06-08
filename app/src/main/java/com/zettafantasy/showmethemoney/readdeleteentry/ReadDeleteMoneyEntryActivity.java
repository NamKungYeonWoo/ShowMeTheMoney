/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zettafantasy.showmethemoney.readdeleteentry;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.zettafantasy.showmethemoney.R;
import com.zettafantasy.showmethemoney.addeditentry.AddEditMoneyEntryActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ReadDeleteMoneyEntryActivity extends AppCompatActivity implements ReadDeleteMoneyEntryContract.View {
    private static final String TAG = ReadDeleteMoneyEntryActivity.class.getSimpleName();

    private ReadDeleteMoneyEntryContract.Presenter mPresenter;
    private Unbinder unbinder;

    @BindView(R.id.rvEntries)
    RecyclerView rvMoneyEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_read_delete_entry);
        unbinder = ButterKnife.bind(this);
        initToolbar();
        initRvMoneyEntries();

        setPresenter(new ReadDeleteEntryPresenter(this, getApplicationContext(), (MoneyEntriesAdapter) rvMoneyEntries.getAdapter()));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startAddMoneyEntryActivity = new Intent(ReadDeleteMoneyEntryActivity.this, AddEditMoneyEntryActivity.class);
                startActivity(startAddMoneyEntryActivity);
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }

    private void initRvMoneyEntries() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvMoneyEntries.setLayoutManager(linearLayoutManager);
        final MoneyEntriesAdapter moneyEntriesAdapter = new MoneyEntriesAdapter(this);
        rvMoneyEntries.setAdapter(moneyEntriesAdapter);
        rvMoneyEntries.setHasFixedSize(true);

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                final Long _id = (Long) viewHolder.itemView.getTag();
                if (_id == null) {
                    Log.e(TAG, "Fail to get entry _id");
                    moneyEntriesAdapter.notifyDataSetChanged();
                    return;
                }

                //삭제여부 확인 팝업
                AlertDialog.Builder builder = new AlertDialog.Builder(ReadDeleteMoneyEntryActivity.this);
                builder.setMessage(R.string.wanna_delete)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mPresenter.delete(_id);
                                mPresenter.showEntries();
                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                moneyEntriesAdapter.notifyDataSetChanged();
                            }
                        });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(rvMoneyEntries);
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mPresenter.showEntries();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setPresenter(ReadDeleteMoneyEntryContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}

