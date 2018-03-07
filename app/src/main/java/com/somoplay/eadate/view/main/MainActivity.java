package com.somoplay.eadate.view.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.somoplay.eadate.R;
import com.somoplay.eadate.view.Interfaces.WebRequestActivityInterface;
import com.somoplay.eadate.view.tabchat.TabFragmentChat_;
import com.somoplay.eadate.view.tabfavourite.TabFragmentFavourite;
import com.somoplay.eadate.view.tabme.TabFragmentMe_;
import com.somoplay.eadate.view.tabnearby.TabFragmentNearby_;
import com.somoplay.eadate.view.tabsearch.TabFragmentSearch_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentByTag;
import org.androidannotations.annotations.ViewById;
import org.json.JSONObject;

@EActivity(R.layout.activity_login_layout)
public class MainActivity extends FragmentActivity implements WebRequestActivityInterface {


    @ViewById(R.id.id_tab_nearby)
    LinearLayout mNearByLayout;
    @ViewById(R.id.id_tab_search)
    LinearLayout mSearchLayout;
    @ViewById(R.id.id_tab_chat)
    LinearLayout mChatLayout;
    @ViewById(R.id.id_tab_favourite)
    LinearLayout mFavouriteLayout;
    @ViewById(R.id.id_tab_me)
    LinearLayout mMeLayout;

    @ViewById(R.id.id_tab_nearby_img)
    ImageButton mButImgNearBy;
    @ViewById(R.id.id_tab_search_img)
    ImageButton mButImgSearch;
    @ViewById(R.id.id_tab_chat_img)
    ImageButton mButImgChat;
    @ViewById(R.id.id_tab_favourite_img)
    ImageButton mButImgFavourite;
    @ViewById(R.id.id_tab_me_img)
    ImageButton mButImgMe;

    @FragmentByTag
    Fragment mTabNearBy;
    @FragmentByTag
    Fragment mTabSearch;
    @FragmentByTag
    Fragment mTabChat;
    @FragmentByTag
    Fragment mTabFavourite;
    @FragmentByTag
    Fragment mTabMe;

    @AfterViews
    void setmButImgNearBy(){
        setSelect(0);
    }

    void setSelect(int i) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        
        hideFragment(transaction);

        switch (i) {
            case 0:
                if (mTabNearBy == null) {
                    mTabNearBy = new TabFragmentNearby_();
                    transaction.add(R.id.id_content, mTabNearBy);
                } else {
                    transaction.show(mTabNearBy);
                }
                mButImgNearBy.setImageResource(R.drawable.tab_nearby_pressed);
                break;
            case 1:
                if (mTabSearch == null) {
                    mTabSearch = new TabFragmentSearch_();
                    transaction.add(R.id.id_content, mTabSearch);
                } else {
                    transaction.show(mTabSearch);

                }
                mButImgSearch.setImageResource(R.drawable.tab_search_pressed);
                break;
            case 2:
                if (mTabChat == null) {
                    mTabChat = new TabFragmentChat_();
                    transaction.add(R.id.id_content, mTabChat);
                } else {
                    transaction.show(mTabChat);
                }
                mButImgChat.setImageResource(R.drawable.tab_chat_pressed);
                break;
            case 3:
                if (mTabFavourite == null) {
                    mTabFavourite = new TabFragmentFavourite();
                    transaction.add(R.id.id_content, mTabFavourite);
                } else {
                    transaction.show(mTabFavourite);
                }
                mButImgFavourite.setImageResource(R.drawable.tab_favorite_pressed);
                break;
            case 4:
                if (mTabMe == null) {
                    mTabMe = new TabFragmentMe_();
                    transaction.add(R.id.id_content, mTabMe);
                } else {
                    transaction.show(mTabMe);
                }
                mButImgMe.setImageResource(R.drawable.tab_me_pressed);
                break;
            default:
                break;
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (mTabNearBy != null) {
            transaction.hide(mTabNearBy);
        }
        if (mTabSearch != null) {
            transaction.hide(mTabSearch);
        }
        if (mTabChat != null) {
            transaction.hide(mTabChat);
        }
        if (mTabFavourite != null) {
            transaction.hide(mTabFavourite);
        }
        if (mTabMe != null) {
            transaction.hide(mTabMe);
        }
    }

    @Click(R.id.id_tab_nearby)
    void setmTabNearBy() {
        resetButImgs();
        setSelect(0);
    }
    @Click(R.id.id_tab_search)
    void setmTabSearch() {
        resetButImgs();
        setSelect(1);
    }
    @Click(R.id.id_tab_chat)
    void setmTabChat() {
        resetButImgs();
        setSelect(2);
    }
    @Click(R.id.id_tab_favourite)
    void setmTabFavourite() {
        resetButImgs();
        setSelect(3);
    }
    @Click(R.id.id_tab_me)
    void setmTabMe() {
        resetButImgs();
        setSelect(4);
    }

    private void resetButImgs() {
        mButImgNearBy.setImageResource(R.drawable.tab_nearby_grey);
        mButImgSearch.setImageResource(R.drawable.tab_search_grey);
        mButImgChat.setImageResource(R.drawable.tab_chat_grey);
        mButImgFavourite.setImageResource(R.drawable.tab_favorite_grey);
        mButImgMe.setImageResource(R.drawable.tab_me_grey);
    }

    @Override
    public JSONObject webRequestCallBack(JSONObject jsonObject)
    {
        return null;
    }
}