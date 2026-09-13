package org.funkymonks.funkyessentials.commons.managers;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ConfigManager {

    private final JavaPlugin plugin;
    private final String fileName;
    private final File file;

    private FileConfiguration fileConfig;

    public ConfigManager(JavaPlugin plugin, String fileName) throws IOException, InvalidConfigurationException {
        this.plugin = plugin;
        this.fileName = fileName;
        this.file = new File(plugin.getDataFolder(), fileName);

        createFileIfAbsent();
        reload();
    }

    private void createFileIfAbsent() throws IOException {
        if (file.exists()) {
            return;
        }

        File parentFolder = file.getParentFile();

        if (parentFolder != null && !parentFolder.exists() && !parentFolder.mkdirs()) {
            throw new IOException("Não foi possível criar a pasta do plugin: " + parentFolder.getPath());
        }

        try (InputStream resource = plugin.getResource(fileName)) {
            if (resource == null) {
                throw new IOException("Arquivo não encontrado dentro do plugin: " + fileName);
            }
        }

        plugin.saveResource(fileName, false);
    }

    public FileConfiguration get() {
        return fileConfig;
    }

    public void validateRequiredKeys(String... keys) throws InvalidConfigurationException {
        for (String key : keys) {
            String value = fileConfig.getString(key);

            if (value == null || value.isBlank()) {
                throw new InvalidConfigurationException(
                        "A configuração " + fileName + " não possui um valor válido para: " + key
                );
            }
        }
    }

    public void save() {
        try {
            fileConfig.save(file);
        } catch (IOException ex) {
            plugin.getLogger().severe("Não foi possível salvar o arquivo " + fileName + ": " + ex.getMessage());
        }
    }

    public void reload() throws IOException, InvalidConfigurationException {
        YamlConfiguration loadedConfig = new YamlConfiguration();
        loadedConfig.load(file);
        this.fileConfig = loadedConfig;
    }
}
