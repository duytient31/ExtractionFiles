/*
 * Copyright 2017 Brian Pellin, Jeremy Jamet / Kunzisoft.
 *     
 * This file is part of KeePass DX.
 *
 *  KeePass DX is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  KeePass DX is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with KeePass DX.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.kunzisoft.keepass.stream;

import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;

public class RandomFileOutputStream extends OutputStream {

	RandomAccessFile mFile;
	
	RandomFileOutputStream(RandomAccessFile file) {
		mFile = file;
	}
	
	@Override
	public void close() throws IOException {
		super.close();
		
		mFile.close();		
	}

	@Override
	public void write(byte[] buffer, int offset, int count) throws IOException {
		super.write(buffer, offset, count);
		
		mFile.write(buffer, offset, count);
	}

	@Override
	public void write(byte[] buffer) throws IOException {
		super.write(buffer);
		
		mFile.write(buffer);
	}

	@Override
	public void write(int oneByte) throws IOException {
		mFile.write(oneByte);
	}
	
	public void seek(long pos) throws IOException {
		mFile.seek(pos);
	}

}
