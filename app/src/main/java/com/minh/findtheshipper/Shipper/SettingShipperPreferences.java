package com.minh.findtheshipper.Shipper;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.minh.findtheshipper.R;

import java.util.List;
import java.util.Locale;

public class SettingShipperPreferences extends PreferenceActivity {
    private static Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            String stringValue = newValue.toString();
            if(preference instanceof ListPreference)
            {
                // For list preferences, look up the correct display value in
                // the preference's 'entries' list.
                ListPreference listPreference = (ListPreference)preference;
                int index = ((ListPreference) preference).findIndexOfValue(stringValue);
                // Set the summary to reflect the new value.
                preference.setSummary(index>=0? listPreference.getEntries()[index]:null);
            }
            else if (preference instanceof RingtonePreference)
            {
                // For ringtone preferences, look up the correct display value
                // using RingtoneManager.
                if(TextUtils.isEmpty(stringValue))
                {
                    // Empty values correspond to 'silent' (no ringtone).
                    preference.setSummary(R.string.pref_ringtone_silent);
                }
                else {
                    Ringtone ringtone = RingtoneManager.getRingtone(preference.getContext(), Uri.parse(stringValue));
                    if(ringtone == null)
                    {
                        preference.setSummary(null);
                    }
                    else {
                        // Set the summary to reflect the new ringtone display
                        String name = ringtone.getTitle(preference.getContext());
                        preference.setSummary(name);

                    }
                }
            }
            else {
                // For all other preferences, set the summary to the value's
                // simple string representation.
                preference.setSummary(stringValue);
            }
            return true;
        }
    };

    /**
     * Helper method to determine if the device has an extra-large screen. For
     * example, 10" tablets are extra-large.
     */
    private static boolean isXLargeTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_XLARGE;
    }

    /**
     * Binds a preference's summary to its value. More specifically, when the
     * preference's value is changed, its summary (line of text below the
     * preference title) is updated to reflect the value. The summary is also
     * immediately updated upon calling this method. The exact display format is
     * dependent on the type of preference.
     */

    private static void bindPreferencesSummaryToValue(Preference preference)
    {
        // Set the listener to watch for value changes.
        preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);
        sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
                PreferenceManager.getDefaultSharedPreferences(preference.getContext()).
                        getString(preference.getKey(),""));

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setActionBar();
    }

    private void setActionBar()
    {

        LinearLayout root = (LinearLayout)findViewById(android.R.id.list).getParent().getParent().getParent();
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) LayoutInflater.from(this).inflate(R.layout.setting_shipper_fragment,root,false);
        toolbar.setTitle(R.string.setting);
        root.addView(toolbar,0); //Add to top
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    public boolean onIsMultiPane() {
        return isXLargeTablet(this);
    }

    @Override
    public void onBuildHeaders(List<Header> target) {
        super.onBuildHeaders(target);
        loadHeadersFromResource(R.xml.pref_headers_shipper,target);
    }

    /**
     * This method stops fragment injection in malicious applications.
     * Make sure to deny any unknown fragments here.
     */

    protected boolean isValidFragment(String fragmentName)
    {
        return DisplayPreferenceFragment.class.getName().equals(fragmentName)
                ||NotificationPreferenceFragment.class.getName().equals(fragmentName)
                ||DataSyncPreferenceFragment.class.getName().equals(fragmentName);
    }


    public static class DisplayPreferenceFragment extends PreferenceFragment
    {
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_display);
            setHasOptionsMenu(true);
            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
            // to their values. When their values change, their summaries are
            // updated to reflect the new value, per the Android Design
            // guidelines.
            bindPreferencesSummaryToValue(findPreference(getResources().getString(R.string.number_order_show)));
            bindPreferencesSummaryToValue(findPreference(getResources().getString(R.string.change_language)));
             final ListPreference listLanguage = (ListPreference)this.findPreference(this.getString(R.string.change_language));

            listLanguage.setSummary(listLanguage.getEntry().toString());

            listLanguage.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    listLanguage.setValue(newValue.toString());
                    preference.setSummary(listLanguage.getEntry());
                    if(listLanguage.getValue().equals("1"))
                    {
                        setLanguage("en-US");
                        listLanguage.setDefaultValue(newValue);
                    }
                    if(listLanguage.getValue().equals("0"))
                    {
                        setLanguage("vi");
                        listLanguage.setDefaultValue(newValue);
                    }
                    return true;
                }
            });


        }

        public void setLanguage(String language)
        {
            Locale locale = new Locale(language);
            Resources resources = getResources();
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            Configuration configuration = resources.getConfiguration();
            configuration.setLocale(locale);
            resources.updateConfiguration(configuration,displayMetrics);
            startActivity(new Intent(getActivity(),SettingShipperPreferences.class));



        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if(id == android.R.id.home)
            {
                startActivity(new Intent(getActivity(),SettingShipperPreferences.class));
            }
            return super.onOptionsItemSelected(item);
        }
    }

    public static class NotificationPreferenceFragment extends PreferenceFragment
    {
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_notification);
            setHasOptionsMenu(true);
            bindPreferencesSummaryToValue(findPreference("notifications_new_message_ringtone"));

        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if(id == android.R.id.home)
            {
                startActivity(new Intent(getActivity(),SettingShipperPreferences.class));
            }
            return super.onOptionsItemSelected(item);
        }
    }

    public static class DataSyncPreferenceFragment extends PreferenceFragment{
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_data_sync);
            setHasOptionsMenu(true);
            bindPreferencesSummaryToValue(findPreference("sync_frequency"));

        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == android.R.id.home) {
                startActivity(new Intent(getActivity(), SettingShipperPreferences.class));
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }
}
