package net.aoba.proxymanager;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.aoba.altmanager.Alt;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ProxyManager {
    private ArrayList<Socks5Proxy> proxies = new ArrayList<Socks5Proxy>();
    private Socks5Proxy activeProxy;

    public ProxyManager() {
        proxies = new ArrayList<>();
        activeProxy = null;
        readProxies();
    }

    public void readProxies() {
        try {
            File proxiesFile = new File("proxies.json");
            if (!proxiesFile.exists()) {
                return; // If file doesn't exist, simply return
            }

            Gson gson = new Gson();
            Type proxiesListType = new TypeToken<List<Socks5Proxy>>(){}.getType();
            FileReader reader = new FileReader(proxiesFile);
            proxies = gson.fromJson(reader, proxiesListType);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveProxies() {
        try {
            File proxiesFile = new File("proxies.json");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileWriter writer = new FileWriter(proxiesFile);
            gson.toJson(proxies, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addProxy(Socks5Proxy proxy) {
        proxies.add(proxy);
        saveProxies();
    }

    public void removeProxy(Socks5Proxy proxy) {
        proxies.remove(proxy);
        if (proxy.equals(activeProxy)) {
            activeProxy = null;
        }
        saveProxies();
    }

    public ArrayList<Socks5Proxy> getProxies() {
        return this.proxies;
    }

    public Socks5Proxy getProxyByIp(String ip) {
        for (Socks5Proxy proxy : proxies) {
            if (proxy.getIp().equals(ip)) {
                return proxy;
            }
        }
        return null;
    }

    public Socks5Proxy getProxyByPort(int port) {
        for (Socks5Proxy proxy : proxies) {
            if (proxy.getPort() == port) {
                return proxy;
            }
        }
        return null;
    }

    public void setActiveProxy(Socks5Proxy proxy) {
        if (proxy == null || proxies.contains(proxy)) {
            activeProxy = proxy;
        } else {
            throw new IllegalArgumentException("Proxy not found in the manager");
        }
    }

    public Socks5Proxy getActiveProxy() {
        return activeProxy;
    }

    public void clearActiveProxy() {
        activeProxy = null;
    }
}