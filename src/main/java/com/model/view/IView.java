package com.model.view;

public interface IView {
    String getTitle();

    void setTitle(String title);

    ViewType getViewType();

    void setViewType(ViewType viewType);

    boolean isPublic();

    void setPublic(boolean aPublic);
}
