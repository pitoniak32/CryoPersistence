package me.cryocell.cryopersistence.config;

public class Constants {
    // General Settings Constants.
    public final static String VERSION_PATH = "version";
    public final static double VERSION_DEFAULT = 1.0;
    public final static String COMMAND_NAME = "cryo-persist";
    public final static String COMMAND_ALIAS = "cp";

    // Permissions Constants.
    public final static String RELOAD_PERMISSION_PATH = "cryo-persist.admin.reload";
    public final static String VERSION_PERMISSION_PATH = "cryo-persist.admin.version";
    public final static String BACKUP_PERMISSION_PATH = "cryo-persist.admin.fbackup";
    public final static String HELP_PERMISSION_PATH = "cryo-persist.admin.help";
    public final static String SAVE_PERMISSION_PATH = "cryo-persist.admin.fsave";

    // Backup Settings Constants.
    public final static String AUTO_BACKUP_INTERVAL_TICKS_PATH = "backup.tickInterval";
    public final static int AUTO_BACKUP_INTERVAL_TICKS_DEFAULT = 1728000;

    public final static String BACKUP_MAX_COUNT_PATH = "backup.max_backups";
    public final static int BACKUP_MAX_COUNT_DEFAULT = 10;
    
    // Save Settings Constants.
    public final static String AUTO_SAVE_INTERVAL_TICKS_PATH = "save.tickInterval";
    public final static int AUTO_SAVE_INTERVAL_TICKS_DEFAULT = 18000;

    // Message Formatting Constants.
    public final static String TAG_FORMAT_BASE_PATH = "format.tag";
    public final static String TAG_FORMAT_BASE_DEFAULT = "[cp]";

    public final static String NO_PERMISSIONS_FORMAT_PATH = "format.permissions";
    public final static String NO_PERMISSIONS_FORMAT_DEFAULT = "no permission";
}
