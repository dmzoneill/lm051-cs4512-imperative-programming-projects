/*
(c) Copyright 2004, 2005 Gary Dusbabek gdusbabek@gmail.com

ALL RIGHTS RESERVED.

By using this software, you acknowlege and agree that:

1. THIS PACKAGE IS PROVIDED "AS IS" AND WITHOUT ANY EXPRESS OR IMPLIED WARRANTIES,
INCLUDING, WITHOUT LIMITATION, THE IMPLIED WARRANTIES OF MERCHANTIBILITY AND
FITNESS FOR A PARTICULAR PURPOSE.

2. This product may be freely copied and distributed in source or binary form
given that the license (this file) and any copyright declarations remain in
tact.

The End
*/

package player.id3;

import java.io.IOException;

/** thrown when a file with no ID3 tag is encountered. */
public class NotATagException
    extends IOException
{
    /** {@inheritDoc}  */
    public NotATagException()
    {
        super();
    }

    /** {@inheritDoc}  */
    public NotATagException(String s)
    {
        super(s);
    }
}
