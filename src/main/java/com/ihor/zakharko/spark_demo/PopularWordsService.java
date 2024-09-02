package com.ihor.zakharko.spark_demo;

import java.io.Serializable;

public interface PopularWordsService extends Serializable {
    long topX();
    long someMethod();
}
