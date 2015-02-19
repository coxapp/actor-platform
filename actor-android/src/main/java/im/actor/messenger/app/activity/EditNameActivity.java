package im.actor.messenger.app.activity;

import android.os.Bundle;

import im.actor.messenger.R;
import im.actor.messenger.app.base.BaseBarFragmentActivity;
import im.actor.messenger.app.fragment.settings.EditNameFragment;
import im.actor.messenger.app.intents.Intents;

/**
 * Created by ex3ndr on 20.10.14.
 */
public class EditNameActivity extends BaseBarFragmentActivity {

    public static final int TYPE_ME = 0;
    public static final int TYPE_USER = 1;
    public static final int TYPE_GROUP = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int type = getIntent().getIntExtra(Intents.EXTRA_EDIT_TYPE, 0);
        int id = getIntent().getIntExtra(Intents.EXTRA_EDIT_ID, 0);

        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(false);
        if (type == TYPE_ME) {
            getSupportActionBar().setTitle(R.string.edit_name_title_you);
        } else if (type == TYPE_USER) {
            getSupportActionBar().setTitle(R.string.edit_name_title_contact);
        } else if (type == TYPE_GROUP) {
            getSupportActionBar().setTitle(R.string.edit_name_title_group);
        }
        showFragment(EditNameFragment.editName(type, id), false, false);
    }
}
