package ru.blurry16.autowb.client;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

import java.util.List;

@Config(name = "autowb")
public class AutowbConfig implements ConfigData {
    public boolean enabled = false;

    public List<String> greetings = List.of("wb", "Wb", "WB", "hi", "hello", "sup", "Hi", "Hello", "Sup");
}
