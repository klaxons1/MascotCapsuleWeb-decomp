package com.hicorp.mascotcapsule.web;

class RenderCommand {
   int commandType;
   RenderCommand next;
   private final RenderContext renderContext;

   private RenderCommand(RenderContext renderContext) {
      this.renderContext = renderContext;
   }

   RenderCommand(RenderContext renderContext, RenderCommandToken unused) {
      this(renderContext);
   }
}
