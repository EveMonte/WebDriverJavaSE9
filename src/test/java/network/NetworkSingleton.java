package network;

import model.Network;

public class NetworkSingleton {
    private static Network network;

    private NetworkSingleton(){}

    public static Network getNetwork() {
        if (null == network) {
            network = new Network();
        }
        return network;
    }

    public static void clearNetwork(){
        network = null;
    }
}
