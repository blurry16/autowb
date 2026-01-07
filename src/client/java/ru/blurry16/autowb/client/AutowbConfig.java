package ru.blurry16.autowb.client;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

import java.util.List;

@Config(name = "autowb")
public class AutowbConfig implements ConfigData {

    public boolean enabled = false;

    public List<String> greetings = List.of("wb", "Wb", "WB", "hi", "hello", "sup", "Hi", "Hello", "Sup");

    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int minTicksDelayBound = 20;  // ticks, inclusive

    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int maxTicksDelayBound = 30; // ticks, inclusive

    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public long minSecondsBetweenWBs = 10L;

    public boolean wbTwiceInARow = false;

    public boolean wbLastWhoLeft = false;

}
