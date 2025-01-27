package com.model.view;

import com.model.SmartList;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListView extends View {
    private SmartList smartList;
    public ListView(String title, SmartList smartList) {
        super(title);
        setViewType(ViewType.LIST);
        this.smartList = smartList;
    }
}
