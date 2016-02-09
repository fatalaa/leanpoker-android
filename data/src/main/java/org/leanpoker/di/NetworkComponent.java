package org.leanpoker.di;

import org.leanpoker.api.NetworkManager;
import org.leanpoker.di.module.NetworkModule;

import dagger.Component;

/**
 * Created by tmolnar on 07/02/16.
 */

@Component(modules = {
        NetworkModule.class
})
public interface NetworkComponent {
    void inject(NetworkManager networkManager);
}
