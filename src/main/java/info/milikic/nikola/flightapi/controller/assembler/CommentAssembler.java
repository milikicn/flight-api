package info.milikic.nikola.flightapi.controller.assembler;

import info.milikic.nikola.flightapi.controller.dto.CommentResponse;
import info.milikic.nikola.flightapi.persistence.model.Comment;

public class CommentAssembler {

    public static CommentResponse createResponse(Comment comment) {
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setId(comment.getId());
        commentResponse.setDescription(comment.getDescription());
        commentResponse.setTimeCreated(comment.getCreated());
        commentResponse.setTimeModified(comment.getModified());
        return commentResponse;
    }

}
