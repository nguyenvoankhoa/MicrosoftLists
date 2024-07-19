package com.view;

import java.util.List;

public class GalleryView extends View {
    List<Card> cards;

    public GalleryView(String title, List<Card> cards) {
        super(title);
        setViewType(ViewType.GALLERY);
        this.cards = cards;
    }
}
