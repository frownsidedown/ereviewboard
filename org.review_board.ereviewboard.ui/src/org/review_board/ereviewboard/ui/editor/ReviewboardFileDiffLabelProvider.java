/*******************************************************************************
 * Copyright (c) 2011 Robert Munteanu and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Robert Munteanu - initial API and implementation
 *******************************************************************************/

package org.review_board.ereviewboard.ui.editor;

import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.mylyn.reviews.core.model.IFileItem;
import org.eclipse.mylyn.reviews.core.model.IReviewItem;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.internal.WorkbenchImages;
import org.review_board.ereviewboard.core.ReviewboardDiffMapper;

/**
 * @author Robert Munteanu
 */
public class ReviewboardFileDiffLabelProvider extends LabelProvider implements IStyledLabelProvider {

    private final ReviewboardDiffMapper _diffMapper;

    public ReviewboardFileDiffLabelProvider(ReviewboardDiffMapper diffMapper) {
        _diffMapper = diffMapper;
    }

    @Override
    public Image getImage(Object element) {
        return WorkbenchImages.getImage(ISharedImages.IMG_OBJ_FILE);
    }

    @Override
    public String getText(Object element) {

        return ((IReviewItem) element).getName();
    }

    public StyledString getStyledText(Object element) {
        String text = getText(element);
        StyledString styledString = new StyledString(text);
        IFileItem fileItem = (IFileItem) element;
        String fileDiffId = fileItem.getId();

        int commentCount = _diffMapper.getCommentCountForFileDiff(Integer.parseInt(fileDiffId));

        if (commentCount > 0)
            styledString.append(" [ " + commentCount + " comments ]", StyledString.DECORATIONS_STYLER);

        return styledString;
    }
}
