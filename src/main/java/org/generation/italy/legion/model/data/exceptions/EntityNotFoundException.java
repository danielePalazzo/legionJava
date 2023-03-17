package org.generation.italy.legion.model.data.exceptions;

import static org.generation.italy.legion.model.data.Constants.ENTITY_NOT_FOUND;

public class EntityNotFoundException extends Exception{

   public EntityNotFoundException(String message) {
      super(message);
   }
   public EntityNotFoundException() {
      super(ENTITY_NOT_FOUND);
   }
}
