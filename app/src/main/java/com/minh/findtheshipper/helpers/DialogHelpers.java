package com.minh.findtheshipper.helpers;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.minh.findtheshipper.R;
import com.minh.findtheshipper.models.Adapters.CustomAdapterListViewNotification;
import com.minh.findtheshipper.models.NotificationObject;
import java.util.ArrayList;
import io.realm.Realm;
import io.realm.RealmResults;


public class DialogHelpers extends DialogFragment {

    private  ListView listNotification;
    private ArrayList<NotificationObject> notificationObjectArrayList;
    private CustomAdapterListViewNotification customAdapterListviewNotification;
    private Realm realm;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm.init(getContext());
        initRealm();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getDialog().getWindow().getAttributes().windowAnimations = R.style.MyDialogAnimation;
        View view = inflater.inflate(R.layout.dialog_fragment,container,false);
        listNotification = (ListView)view.findViewById(R.id.listNotification);
        Toolbar toolbar = (Toolbar)view.findViewById(R.id.toolBar);
        toolbar.setTitle(getResources().getString(R.string.notification_title));
        loadAllNotification();
        return view;
    }

    public void initRealm()
    {
        realm = null;
        realm = Realm.getDefaultInstance();
    }

    public void loadAllNotification()
    {
        notificationObjectArrayList = new ArrayList<>();
        RealmResults<NotificationObject> notificationObjectRealmResults = realm.where(NotificationObject.class).findAll();
        for(int i = 0 ; i < notificationObjectRealmResults.size(); i++)
        {
            notificationObjectArrayList.add(notificationObjectRealmResults.get(i));
        }
      //  customAdapterListviewNotification = new CustomAdapterListViewNotification(getContext(), notificationObjectArrayList);
     //   listNotification.setAdapter(customAdapterListviewNotification);
    }

    @Override
    public void onResume() {
        super.onResume();
        int width = getResources().getDimensionPixelSize(R.dimen.dialog_width);
        int height = getResources().getDimensionPixelSize(R.dimen.dialog_height);
        getDialog().getWindow().setLayout(width,height);
        /*Window window = getDialog().getWindow();
        Point size = new Point();
        // Store dimensions of the screen in `size`
        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);
        // Set the width of the dialog proportional to 75% of the screen width
        window.setLayout((int) (size.x * 0.75), WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        // Call super onResume after sizing
        */

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
       realm.close();
    }


}
