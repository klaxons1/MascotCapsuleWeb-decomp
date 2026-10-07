package com.hicorp.mascotcapsule.web;

import java.io.InputStream;

public interface ImageDecoder {
   boolean readImage(InputStream in, Texture texture);
}
