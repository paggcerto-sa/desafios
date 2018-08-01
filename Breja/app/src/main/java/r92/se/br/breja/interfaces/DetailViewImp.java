package r92.se.br.breja.interfaces;

import android.content.Context;

public interface DetailViewImp {

    void updateName(String name);
    void updateImage(String url);
    void updateFabIcon(int idResource);
    void updateVisibilityFab(Integer id);
    Context getContext();
}
