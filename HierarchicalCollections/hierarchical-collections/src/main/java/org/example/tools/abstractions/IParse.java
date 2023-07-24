package org.example.tools.abstractions;

import org.example.model.Geography;

public interface IParse<TResult> {

    TResult parse(String line);
}
