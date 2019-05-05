package per.yunfan.cse406.musicplayer.service;

import per.yunfan.cse406.musicplayer.service.music.MusicServiceImpl;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public interface MusicService extends Remote, RMIService<MusicService> {
    static MusicService instance() throws RemoteException {
        return MusicServiceImpl.INSTANCE;
    }

    @Override
    default MusicService getClient(String host, int port) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(host, port);
        return (MusicService) registry.lookup(this.nameOfRMI());
    }
}
