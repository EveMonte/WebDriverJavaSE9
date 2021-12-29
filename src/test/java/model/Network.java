package model;

import java.util.Objects;

public class Network {
    private String networkName;
    private String newURLRPC;
    private String chainIdentifier;

    public String getNetworkName() {
        return networkName;
    }

    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }

    public String getNewURLRPC() {
        return newURLRPC;
    }

    public void setNewURLRPC(String newURLRPC) {
        this.newURLRPC = newURLRPC;
    }

    public String getChainIdentifier() {
        return chainIdentifier;
    }

    public void setChainIdentifier(String chainIdentifier) {
        this.chainIdentifier = chainIdentifier;
    }

    public Network(String networkName, String newURLRPC, String chainIdentifier) {
        this.networkName = networkName;
        this.newURLRPC = newURLRPC;
        this.chainIdentifier = chainIdentifier;
    }

    public Network() {
    }

    @Override
    public String toString() {
        return "Network{" +
                "networkName='" + networkName + '\'' +
                ", newURLRPC='" + newURLRPC + '\'' +
                ", chainIdentifier=" + chainIdentifier +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Network network = (Network) o;
        return chainIdentifier == network.chainIdentifier && Objects.equals(networkName, network.networkName) && Objects.equals(newURLRPC, network.newURLRPC);
    }

    @Override
    public int hashCode() {
        return Objects.hash(networkName, newURLRPC, chainIdentifier);
    }
}
