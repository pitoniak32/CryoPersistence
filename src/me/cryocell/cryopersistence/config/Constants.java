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
    public final static String BACKUP_PERMISSION_PATH = "cryo-persist.admin.force-backup";
    public final static String HELP_PERMISSION_PATH = "cryo-persist.admin.help";
    public final static String SAVE_PERMISSION_PATH = "cryo-persist.admin.force-save";

    // Backup Settings Constants.
    public final static String AUTO_BACKUP_ON_PATH = "backup.enabled";
    public final static boolean AUTO_BACKUP_ON_DEFAULT = true;

    public final static String AUTO_BACKUP_INTERVAL_TICKS_PATH = "backup.tickInterval";
    public final static int AUTO_BACKUP_INTERVAL_TICKS_DEFAULT = 1728000;

    public final static String BACKUP_WORLD_FILES_PATH = "backup.worldsToBackup";
    public final static String [] BACKUP_WORLD_FILES_DEFAULT = new String[] { "world", "world_nether", "world_the_end" };

    public final static String BACKUP_MAX_FOLDER_SIZE_MB_PATH = "backup.folder.max_folder_size";
    public final static int BACKUP_MAX_FOLDER_SIZE_MB_DEFAULT = 1024;

    public final static String BACKUP_MAX_COUNT_PATH = "backup.folder.max_folder_number";
    public final static int BACKUP_MAX_COUNT_DEFAULT = 10;
    
    // Save Settings Constants.
    public final static String AUTO_SAVE_ON_PATH = "save.enabled";
    public final static boolean AUTO_SAVE_ON_DEFAULT = true;

    public final static String AUTO_SAVE_INTERVAL_TICKS_PATH = "save.tickInterval";
    public final static int AUTO_SAVE_INTERVAL_TICKS_DEFAULT = 18000;

    // Message Formatting Constants.
    public final static String TAG_FORMAT_BASE_PATH = "format.tag.base";
    public final static String TAG_FORMAT_BASE_DEFAULT = "[cp]";

    public final static String NO_PERMISSIONS_FORMAT_PATH = "format.permissions";
    public final static String NO_PERMISSIONS_FORMAT_DEFAULT = "no permission.";

    public final static String NOT_FOUND_FORMAT_PATH = "format.noWorld";
    public final static String NOT_FOUND_FORMAT_DEFAULT = "world not found in config.";

    public final static String RELOADED_FORMAT_PATH = "format.reload";
    public final static String RELOADED_FORMAT_DEFAULT = "reloaded.";

    public final static String BACKUP_STARTED_FORMAT_PATH = "format.backing-up";
    public final static String BACKUP_STARTED_FORMAT_DEFAULT = "starting new backup...";

    public final static String SAVE_STARTED_FORMAT_PATH = "format.saving";
    public final static String SAVE_STARTED_FORMAT_DEFAULT = "starting saves...";
}
