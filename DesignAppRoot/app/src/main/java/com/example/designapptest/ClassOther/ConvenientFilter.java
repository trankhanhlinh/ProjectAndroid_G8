package com.example.designapptest.ClassOther;

public class ConvenientFilter extends myFilter {

    int imageResource;

    public ConvenientFilter(){

    }

    public ConvenientFilter(String name, String id, int imageResource) {
        this.name = name;
        this.id = id;
        this.imageResource = imageResource;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    @Override
    public void replace(myFilter filter) {
        if(filter instanceof ConvenientFilter){

        }
    }
}
