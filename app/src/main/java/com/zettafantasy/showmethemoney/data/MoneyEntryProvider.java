package com.zettafantasy.showmethemoney.data;

import android.net.Uri;

import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.InexactContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;

/**
 * Created by UJ on 2017-02-08.
 */

@ContentProvider(authority = MoneyEntryProvider.AUTHORITY, database = MoneyEntryDatabase.class)
public final class MoneyEntryProvider {
    public static final String AUTHORITY =
            "com.zettafantasy.showmethemoney.data.MoneyEntryProvider";
    static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    interface Path {
        String MONEY_ENTRIES = "money_entries";
    }

    private static Uri buildUri(String... paths) {
        Uri.Builder builder = BASE_CONTENT_URI.buildUpon();
        for (String path : paths) {
            builder.appendPath(path);
        }
        return builder.build();
    }

    @TableEndpoint(table = MoneyEntryDatabase.MONEY_ENTRIES)
    public static class MoneyEntries {
        @ContentUri(
                path = Path.MONEY_ENTRIES,
                type = "vnd.android.cursor.dir/money_entry")
        public static final Uri CONTENT_URI = buildUri(Path.MONEY_ENTRIES);


        @InexactContentUri(
                path = Path.MONEY_ENTRIES + "/#",
                name = "MOVIES_BY_ID",
                type = "vnd.android.cursor.item/money_entry",
                whereColumn = MoneyEntryColumns._ID,
                pathSegment = 1)
        public static Uri withId(long id) {
            return buildUri(Path.MONEY_ENTRIES, String.valueOf(id));
        }
    }
}