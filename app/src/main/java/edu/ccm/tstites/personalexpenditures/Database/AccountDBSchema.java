package edu.ccm.tstites.personalexpenditures.Database;

/**
 * Created by tstites on 4/26/2018.
 */

public class AccountDBSchema {
    public static final class Accounts {
        public static final String NAME = "accounts";

        public static final class Columns {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String CATEGORY = "category";
            public static final String LOCATION = "location";
        }
    }
}
