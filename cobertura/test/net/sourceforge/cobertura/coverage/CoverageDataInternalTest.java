/**
 * Cobertura - http://cobertura.sourceforge.net/
 *
 * Copyright (C) 2003 jcoverage ltd.
 * Copyright (C) 2005 Mark Doliner <thekingant@users.sourceforge.net>
 *
 * Cobertura is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published
 * by the Free Software Foundation; either version 2 of the License,
 * or (at your option) any later version.
 *
 * Cobertura is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Cobertura; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA
 */

package net.sourceforge.cobertura.coverage;

import java.util.Set;
import java.util.TreeSet;

import junit.framework.TestCase;

public class CoverageDataInternalTest extends TestCase
{

	final CoverageDataInternal a = new CoverageDataInternalImpl();
	final CoverageDataInternal b = new CoverageDataInternalImpl();
	final CoverageDataInternal c = new CoverageDataInternalImpl();

	public void setUp()
	{
		Set sourceLineNumbers = new TreeSet();
		sourceLineNumbers.add(new Integer(1));
		sourceLineNumbers.add(new Integer(2));
		sourceLineNumbers.add(new Integer(3));
		sourceLineNumbers.add(new Integer(4));

		b.setSourceLineNumbers(sourceLineNumbers);
		c.setSourceLineNumbers(sourceLineNumbers);

		b.touch(1);
		b.touch(2);
	}

	public void testTouch()
	{
		int line = 5;

		assertFalse(a.isValidSourceLineNumber(line));
		assertEquals(0, a.getHitCount(line));
		a.touch(line);
		assertTrue(a.isValidSourceLineNumber(line));
		assertEquals(1, a.getHitCount(line));
		a.touch(line);
		assertEquals(2, a.getHitCount(line));
		assertTrue(a.isValidSourceLineNumber(line));
	}

	public void testGetLineCoverageRate()
	{
		assertEquals(1d, a.getLineCoverageRate(), 0d);
		assertEquals(0.5d, b.getLineCoverageRate(), 0d);
		assertEquals(0d, c.getLineCoverageRate(), 0d);
	}
}