# CryoPersistence

This is a plugin that allows for the world to be saved and or backed-up on tickIntervals.

The `.jar` is in the `out` folder, to use this plugin place the jar into the plugins' folder in your server.  

You need to have the option `--world-container worlds` after the last item in the start server command  

ex: `java -Xms5G -Xmx5G -XX:+UseG1GC -jar spigot.jar nogui --world-container worlds`

This allows the plugin to zip the `worlds` folder and store it as a backup.  

## Configuration
To disable a feature set the `tickInterval` to 0, this will cause the Tasks to not be scheduled. You can still force save/backup when the interval is 0.  

The `max_backups` allows you to set the max number of backups to store. When the number reaches the max it will remove the oldest backup zip.
### example config.yml
```yaml
save:
  tickInterval: 18000
backup:
  tickInterval: 1728000
  max_backups: 10
version: 1.0
format:
  tag: '&8[&b&lcp&8]'
  permissions: no permission
```