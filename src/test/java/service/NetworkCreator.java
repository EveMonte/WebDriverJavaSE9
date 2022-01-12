package service;

import model.Network;
import network.NetworkSingleton;
import util.StringUtils;

public class NetworkCreator {
    public static final String networkName = StringUtils.generateRandomNetworkNameWithPostfixLength(5);
    public static final String chainIdentifier = "1337";
    public static final String newURLRPC = "HTTP://127.0.0.1:7545";

    public static void withDataFromProperties(){
        NetworkSingleton.getNetwork().setNetworkName(networkName);
        NetworkSingleton.getNetwork().setChainIdentifier(chainIdentifier);
        NetworkSingleton.getNetwork().setNewURLRPC(newURLRPC);
    }
}
