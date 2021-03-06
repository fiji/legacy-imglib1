package mpicbg.imglib.wrapper;

import java.util.ArrayList;

import ij.ImageJ;
import mpicbg.imglib.container.array.Array;
import mpicbg.imglib.container.array.ArrayContainerFactory;
import mpicbg.imglib.image.Image;
import mpicbg.imglib.image.ImageFactory;
import mpicbg.imglib.image.display.imagej.ImageJFunctions;
import net.imglib2.Cursor;
import net.imglib2.img.Img;
import net.imglib2.img.ImgFactory;
import net.imglib2.img.array.ArrayImg;
import net.imglib2.img.array.ArrayImgFactory;
import net.imglib2.img.basictypeaccess.ByteAccess;
import net.imglib2.img.basictypeaccess.DoubleAccess;
import net.imglib2.img.basictypeaccess.FloatAccess;
import net.imglib2.img.basictypeaccess.IntAccess;
import net.imglib2.img.basictypeaccess.LongAccess;
import net.imglib2.img.basictypeaccess.ShortAccess;
import net.imglib2.img.basictypeaccess.array.ByteArray;
import net.imglib2.img.basictypeaccess.array.DoubleArray;
import net.imglib2.img.basictypeaccess.array.FloatArray;
import net.imglib2.img.basictypeaccess.array.IntArray;
import net.imglib2.img.basictypeaccess.array.LongArray;
import net.imglib2.img.basictypeaccess.array.ShortArray;
import net.imglib2.img.cell.Cell;
import net.imglib2.img.cell.CellImg;
import net.imglib2.img.cell.CellImgFactory;
import net.imglib2.img.list.ListImg;
import net.imglib2.type.numeric.integer.ByteType;
import net.imglib2.type.numeric.integer.IntType;
import net.imglib2.type.numeric.integer.LongType;
import net.imglib2.type.numeric.integer.ShortType;
import net.imglib2.type.numeric.integer.UnsignedByteType;
import net.imglib2.type.numeric.integer.UnsignedIntType;
import net.imglib2.type.numeric.integer.UnsignedShortType;
import net.imglib2.type.numeric.real.DoubleType;
import net.imglib2.type.numeric.real.FloatType;
import net.imglib2.util.Util;

/**
 * Provides non-copying wrapping from Imglib2 to Imglib1 (cell, array)
 *
 * @author Stephan Preibisch (stephan.preibisch@gmx.de)
 */
public class ImgLib2
{
	/**
	 * Wrap an ImgLib2 {@link Img} of FloatType into an ImgLib1 {@link Image} (supports Cell and Array)
	 *
	 * @param image - the ImgLib1 input image
	 * @return - the wrapped ImgLib2 image
	 */
	public static Image< mpicbg.imglib.type.numeric.real.FloatType > wrapFloatToImgLib1 ( final Img< FloatType > img )
	{
		if ( img instanceof ArrayImg )
			return wrapArrayFloatToImgLib1( img );
		if ( img instanceof CellImg )
			return wrapCellFloatToImgLib1( img );
		else
			throw new RuntimeException( img.getClass().getCanonicalName() + " not supported." );
	}

	/**
	 * Wrap an ImgLib2 {@link Img} of DoubleType into an ImgLib1 {@link Image} (supports Cell and Array)
	 *
	 * @param image - the ImgLib1 input image
	 * @return - the wrapped ImgLib2 image
	 */
	public static Image< mpicbg.imglib.type.numeric.real.DoubleType > wrapDoubleToImgLib1 ( final Img< DoubleType > img )
	{
		if ( img instanceof ArrayImg )
			return wrapArrayDoubleToImgLib1( img );
		if ( img instanceof CellImg )
			return wrapCellDoubleToImgLib1( img );
		else
			throw new RuntimeException( img.getClass().getCanonicalName() + " not supported." );
	}

	/**
	 * Wrap an ImgLib2 {@link Img} of LongType into an ImgLib1 {@link Image} (supports Cell and Array)
	 *
	 * @param image - the ImgLib1 input image
	 * @return - the wrapped ImgLib2 image
	 */
	public static Image< mpicbg.imglib.type.numeric.integer.LongType > wrapLongToImgLib1 ( final Img< LongType > img )
	{
		if ( img instanceof ArrayImg )
			return wrapArrayLongToImgLib1( img );
		if ( img instanceof CellImg )
			return wrapCellLongToImgLib1( img );
		else
			throw new RuntimeException( img.getClass().getCanonicalName() + " not supported." );
	}

	/**
	 * Wrap an ImgLib2 {@link Img} of IntType into an ImgLib1 {@link Image} (supports Cell and Array)
	 *
	 * @param image - the ImgLib1 input image
	 * @return - the wrapped ImgLib2 image
	 */
	public static Image< mpicbg.imglib.type.numeric.integer.IntType > wrapIntToImgLib1 ( final Img< IntType > img )
	{
		if ( img instanceof ArrayImg )
			return wrapArrayIntToImgLib1( img );
		if ( img instanceof CellImg )
			return wrapCellIntToImgLib1( img );
		else
			throw new RuntimeException( img.getClass().getCanonicalName() + " not supported." );
	}

	/**
	 * Wrap an ImgLib2 {@link Img} of UnsignedIntType into an ImgLib1 {@link Image} (supports Cell and Array)
	 *
	 * @param image - the ImgLib1 input image
	 * @return - the wrapped ImgLib2 image
	 */
	public static Image< mpicbg.imglib.type.numeric.integer.UnsignedIntType > wrapUnsignedIntToImgLib1 ( final Img< UnsignedIntType > img )
	{
		if ( img instanceof ArrayImg )
			return wrapArrayUnsignedIntToImgLib1( img );
		if ( img instanceof CellImg )
			return wrapCellUnsignedIntToImgLib1( img );
		else
			throw new RuntimeException( img.getClass().getCanonicalName() + " not supported." );
	}

	/**
	 * Wrap an ImgLib2 {@link Img} of ShortType into an ImgLib1 {@link Image} (supports Cell and Array)
	 *
	 * @param image - the ImgLib1 input image
	 * @return - the wrapped ImgLib2 image
	 */
	public static Image< mpicbg.imglib.type.numeric.integer.ShortType > wrapShortToImgLib1 ( final Img< ShortType > img )
	{
		if ( img instanceof ArrayImg )
			return wrapArrayShortToImgLib1( img );
		if ( img instanceof CellImg )
			return wrapCellShortToImgLib1( img );
		else
			throw new RuntimeException( img.getClass().getCanonicalName() + " not supported." );
	}

	/**
	 * Wrap an ImgLib2 {@link Img} of UnsignedShortType into an ImgLib1 {@link Image} (supports Cell and Array)
	 *
	 * @param image - the ImgLib1 input image
	 * @return - the wrapped ImgLib2 image
	 */
	public static Image< mpicbg.imglib.type.numeric.integer.UnsignedShortType > wrapUnsignedShortToImgLib1 ( final Img< UnsignedShortType > img )
	{
		if ( img instanceof ArrayImg )
			return wrapArrayUnsignedShortToImgLib1( img );
		if ( img instanceof CellImg )
			return wrapCellUnsignedShortToImgLib1( img );
		else
			throw new RuntimeException( img.getClass().getCanonicalName() + " not supported." );
	}

	/**
	 * Wrap an ImgLib2 {@link Img} of ByteType into an ImgLib1 {@link Image} (supports Cell and Array)
	 *
	 * @param image - the ImgLib1 input image
	 * @return - the wrapped ImgLib2 image
	 */
	public static Image< mpicbg.imglib.type.numeric.integer.ByteType > wrapByteToImgLib1 ( final Img< ByteType > img )
	{
		if ( img instanceof ArrayImg )
			return wrapArrayByteToImgLib1( img );
		if ( img instanceof CellImg )
			return wrapCellByteToImgLib1( img );
		else
			throw new RuntimeException( img.getClass().getCanonicalName() + " not supported." );
	}

	/**
	 * Wrap an ImgLib2 {@link Img} of UnsignedByteType into an ImgLib1 {@link Image} (supports Cell and Array)
	 *
	 * @param image - the ImgLib1 input image
	 * @return - the wrapped ImgLib2 image
	 */
	public static Image< mpicbg.imglib.type.numeric.integer.UnsignedByteType > wrapUnsignedByteToImgLib1 ( final Img< UnsignedByteType > img )
	{
		if ( img instanceof ArrayImg )
			return wrapArrayUnsignedByteToImgLib1( img );
		if ( img instanceof CellImg )
			return wrapCellUnsignedByteToImgLib1( img );
		else
			throw new RuntimeException( img.getClass().getCanonicalName() + " not supported." );
	}

	/**
	 * wraps an ImgLib2 {@link CellImg} of FloatType (has to be CellImg) into an ImgLib1 {@link Image}
	 *
	 * @param image - input image
	 * @return
	 */
	public static Image< mpicbg.imglib.type.numeric.real.FloatType > wrapCellFloatToImgLib1 ( final Img< FloatType > img )
	{
		// extract float[] arrays from the CellImg consisting of default cells
		@SuppressWarnings( "unchecked" )
		final CellImg< FloatType, FloatArray > cellImg = (CellImg< FloatType, FloatArray > )img;
		final ListImg< Cell< FloatArray > > cells = cellImg.getCells();

		final ArrayList< float[] > celldata = new ArrayList<>( );
		final Cursor< Cell< FloatArray > > cursor = cells.cursor();

		while ( cursor.hasNext() )
			celldata.add( cursor.next().getData().getCurrentStorageArray() );

		// get the cell size and image size
		final int[] cellSize = new int[ img.numDimensions() ];
		cellImg.getCellGrid().cellDimensions( cellSize );
		final int dim[] = Util.long2int( cellImg.getCellGrid().getImgDimensions() );

		// instantiate the new cell container on imglib1
		final PredefinedCellContainerFactory factory = new PredefinedCellContainerFactory( cellSize, new ExistingFloatArrays( celldata ) );
		final ImageFactory< mpicbg.imglib.type.numeric.real.FloatType > imgFactory =
				new ImageFactory<>( new mpicbg.imglib.type.numeric.real.FloatType(), factory );

		final Image< mpicbg.imglib.type.numeric.real.FloatType > img1 = imgFactory.createImage( dim );
		factory.disableWrapping();

		return img1;
	}

	/**
	 * wraps an ImgLib2 {@link CellImg} of DoubleType (has to be CellImg) into an ImgLib1 {@link Image}
	 *
	 * @param image - input image
	 * @return
	 */
	public static Image< mpicbg.imglib.type.numeric.real.DoubleType > wrapCellDoubleToImgLib1 ( final Img< DoubleType > img )
	{
		// extract Double[] arrays from the CellImg consisting of default cells
		@SuppressWarnings( "unchecked" )
		final CellImg< DoubleType, DoubleArray > cellImg = (CellImg< DoubleType, DoubleArray > )img;
		final ListImg< Cell< DoubleArray > > cells = cellImg.getCells();

		final ArrayList< double[] > celldata = new ArrayList<>( );
		final Cursor< Cell< DoubleArray > > cursor = cells.cursor();

		while ( cursor.hasNext() )
			celldata.add( cursor.next().getData().getCurrentStorageArray() );

		// get the cell size and image size
		final int[] cellSize = new int[ img.numDimensions() ];
		cellImg.getCellGrid().cellDimensions( cellSize );
		final int dim[] = Util.long2int( cellImg.getCellGrid().getImgDimensions() );

		// instantiate the new cell container on imglib1
		final PredefinedCellContainerFactory factory = new PredefinedCellContainerFactory( cellSize, new ExistingDoubleArrays( celldata ) );
		final ImageFactory< mpicbg.imglib.type.numeric.real.DoubleType > imgFactory =
				new ImageFactory<>( new mpicbg.imglib.type.numeric.real.DoubleType(), factory );

		final Image< mpicbg.imglib.type.numeric.real.DoubleType > img1 = imgFactory.createImage( dim );
		factory.disableWrapping();

		return img1;
	}

	/**
	 * wraps an ImgLib2 {@link CellImg} of LongType (has to be CellImg) into an ImgLib1 {@link Image}
	 *
	 * @param image - input image
	 * @return
	 */
	public static Image< mpicbg.imglib.type.numeric.integer.LongType > wrapCellLongToImgLib1 ( final Img< LongType > img )
	{
		// extract Long[] arrays from the CellImg consisting of default cells
		@SuppressWarnings( "unchecked" )
		final CellImg< LongType, LongArray > cellImg = (CellImg< LongType, LongArray > )img;
		final ListImg< Cell< LongArray > > cells = cellImg.getCells();

		final ArrayList< long[] > celldata = new ArrayList<>( );
		final Cursor< Cell< LongArray > > cursor = cells.cursor();

		while ( cursor.hasNext() )
			celldata.add( cursor.next().getData().getCurrentStorageArray() );

		// get the cell size and image size
		final int[] cellSize = new int[ img.numDimensions() ];
		cellImg.getCellGrid().cellDimensions( cellSize );
		final int dim[] = Util.long2int( cellImg.getCellGrid().getImgDimensions() );

		// instantiate the new cell container on imglib1
		final PredefinedCellContainerFactory factory = new PredefinedCellContainerFactory( cellSize, new ExistingLongArrays( celldata ) );
		final ImageFactory< mpicbg.imglib.type.numeric.integer.LongType > imgFactory =
				new ImageFactory<>( new mpicbg.imglib.type.numeric.integer.LongType(), factory );

		final Image< mpicbg.imglib.type.numeric.integer.LongType > img1 = imgFactory.createImage( dim );
		factory.disableWrapping();

		return img1;
	}

	/**
	 * wraps an ImgLib2 {@link CellImg} of IntType (has to be CellImg) into an ImgLib1 {@link Image}
	 *
	 * @param image - input image
	 * @return
	 */
	public static Image< mpicbg.imglib.type.numeric.integer.IntType > wrapCellIntToImgLib1 ( final Img< IntType > img )
	{
		// extract Int[] arrays from the CellImg consisting of default cells
		@SuppressWarnings( "unchecked" )
		final CellImg< IntType, IntArray > cellImg = (CellImg< IntType, IntArray > )img;
		final ListImg< Cell< IntArray > > cells = cellImg.getCells();

		final ArrayList< int[] > celldata = new ArrayList<>( );
		final Cursor< Cell< IntArray > > cursor = cells.cursor();

		while ( cursor.hasNext() )
			celldata.add( cursor.next().getData().getCurrentStorageArray() );

		// get the cell size and image size
		final int[] cellSize = new int[ img.numDimensions() ];
		cellImg.getCellGrid().cellDimensions( cellSize );
		final int dim[] = Util.long2int( cellImg.getCellGrid().getImgDimensions() );

		// instantiate the new cell container on imglib1
		final PredefinedCellContainerFactory factory = new PredefinedCellContainerFactory( cellSize, new ExistingIntArrays( celldata ) );
		final ImageFactory< mpicbg.imglib.type.numeric.integer.IntType > imgFactory =
				new ImageFactory<>( new mpicbg.imglib.type.numeric.integer.IntType(), factory );

		final Image< mpicbg.imglib.type.numeric.integer.IntType > img1 = imgFactory.createImage( dim );
		factory.disableWrapping();

		return img1;
	}

	/**
	 * wraps an ImgLib2 {@link CellImg} of UnsignedIntType (has to be CellImg) into an ImgLib1 {@link Image}
	 *
	 * @param image - input image
	 * @return
	 */
	public static Image< mpicbg.imglib.type.numeric.integer.UnsignedIntType > wrapCellUnsignedIntToImgLib1 ( final Img< UnsignedIntType > img )
	{
		// extract Int[] arrays from the CellImg consisting of default cells
		@SuppressWarnings( "unchecked" )
		final CellImg< UnsignedIntType, IntArray > cellImg = (CellImg< UnsignedIntType, IntArray > )img;
		final ListImg< Cell< IntArray > > cells = cellImg.getCells();

		final ArrayList< int[] > celldata = new ArrayList<>( );
		final Cursor< Cell< IntArray > > cursor = cells.cursor();

		while ( cursor.hasNext() )
			celldata.add( cursor.next().getData().getCurrentStorageArray() );

		// get the cell size and image size
		final int[] cellSize = new int[ img.numDimensions() ];
		cellImg.getCellGrid().cellDimensions( cellSize );
		final int dim[] = Util.long2int( cellImg.getCellGrid().getImgDimensions() );

		// instantiate the new cell container on imglib1
		final PredefinedCellContainerFactory factory = new PredefinedCellContainerFactory( cellSize, new ExistingIntArrays( celldata ) );
		final ImageFactory< mpicbg.imglib.type.numeric.integer.UnsignedIntType > imgFactory =
				new ImageFactory<>( new mpicbg.imglib.type.numeric.integer.UnsignedIntType(), factory );

		final Image< mpicbg.imglib.type.numeric.integer.UnsignedIntType > img1 = imgFactory.createImage( dim );
		factory.disableWrapping();

		return img1;
	}

	/**
	 * wraps an ImgLib2 {@link CellImg} of ShortType (has to be CellImg) into an ImgLib1 {@link Image}
	 *
	 * @param image - input image
	 * @return
	 */
	public static Image< mpicbg.imglib.type.numeric.integer.ShortType > wrapCellShortToImgLib1 ( final Img< ShortType > img )
	{
		// extract Short[] arrays from the CellImg consisting of default cells
		@SuppressWarnings( "unchecked" )
		final CellImg< ShortType, ShortArray > cellImg = (CellImg< ShortType, ShortArray > )img;
		final ListImg< Cell< ShortArray > > cells = cellImg.getCells();

		final ArrayList< short[] > celldata = new ArrayList<>( );
		final Cursor< Cell< ShortArray > > cursor = cells.cursor();

		while ( cursor.hasNext() )
			celldata.add( cursor.next().getData().getCurrentStorageArray() );

		// get the cell size and image size
		final int[] cellSize = new int[ img.numDimensions() ];
		cellImg.getCellGrid().cellDimensions( cellSize );
		final int dim[] = Util.long2int( cellImg.getCellGrid().getImgDimensions() );

		// instantiate the new cell container on imglib1
		final PredefinedCellContainerFactory factory = new PredefinedCellContainerFactory( cellSize, new ExistingShortArrays( celldata ) );
		final ImageFactory< mpicbg.imglib.type.numeric.integer.ShortType > imgFactory =
				new ImageFactory<>( new mpicbg.imglib.type.numeric.integer.ShortType(), factory );

		final Image< mpicbg.imglib.type.numeric.integer.ShortType > img1 = imgFactory.createImage( dim );
		factory.disableWrapping();

		return img1;
	}

	/**
	 * wraps an ImgLib2 {@link CellImg} of UnsignedShortType (has to be CellImg) into an ImgLib1 {@link Image}
	 *
	 * @param image - input image
	 * @return
	 */
	public static Image< mpicbg.imglib.type.numeric.integer.UnsignedShortType > wrapCellUnsignedShortToImgLib1 ( final Img< UnsignedShortType > img )
	{
		// extract Short[] arrays from the CellImg consisting of default cells
		@SuppressWarnings( "unchecked" )
		final CellImg< UnsignedShortType, ShortArray > cellImg = (CellImg< UnsignedShortType, ShortArray > )img;
		final ListImg< Cell< ShortArray > > cells = cellImg.getCells();

		final ArrayList< short[] > celldata = new ArrayList<>( );
		final Cursor< Cell< ShortArray > > cursor = cells.cursor();

		while ( cursor.hasNext() )
			celldata.add( cursor.next().getData().getCurrentStorageArray() );

		// get the cell size and image size
		final int[] cellSize = new int[ img.numDimensions() ];
		cellImg.getCellGrid().cellDimensions( cellSize );
		final int dim[] = Util.long2int( cellImg.getCellGrid().getImgDimensions() );

		// instantiate the new cell container on imglib1
		final PredefinedCellContainerFactory factory = new PredefinedCellContainerFactory( cellSize, new ExistingShortArrays( celldata ) );
		final ImageFactory< mpicbg.imglib.type.numeric.integer.UnsignedShortType > imgFactory =
				new ImageFactory<>( new mpicbg.imglib.type.numeric.integer.UnsignedShortType(), factory );

		final Image< mpicbg.imglib.type.numeric.integer.UnsignedShortType > img1 = imgFactory.createImage( dim );
		factory.disableWrapping();

		return img1;
	}

	/**
	 * wraps an ImgLib2 {@link CellImg} of ByteType (has to be CellImg) into an ImgLib1 {@link Image}
	 *
	 * @param image - input image
	 * @return
	 */
	public static Image< mpicbg.imglib.type.numeric.integer.ByteType > wrapCellByteToImgLib1 ( final Img< ByteType > img )
	{
		// extract Byte[] arrays from the CellImg consisting of default cells
		@SuppressWarnings( "unchecked" )
		final CellImg< ByteType, ByteArray > cellImg = (CellImg< ByteType, ByteArray > )img;
		final ListImg< Cell< ByteArray > > cells = cellImg.getCells();

		final ArrayList< byte[] > celldata = new ArrayList<>( );
		final Cursor< Cell< ByteArray > > cursor = cells.cursor();

		while ( cursor.hasNext() )
			celldata.add( cursor.next().getData().getCurrentStorageArray() );

		// get the cell size and image size
		final int[] cellSize = new int[ img.numDimensions() ];
		cellImg.getCellGrid().cellDimensions( cellSize );
		final int dim[] = Util.long2int( cellImg.getCellGrid().getImgDimensions() );

		// instantiate the new cell container on imglib1
		final PredefinedCellContainerFactory factory = new PredefinedCellContainerFactory( cellSize, new ExistingByteArrays( celldata ) );
		final ImageFactory< mpicbg.imglib.type.numeric.integer.ByteType > imgFactory =
				new ImageFactory<>( new mpicbg.imglib.type.numeric.integer.ByteType(), factory );

		final Image< mpicbg.imglib.type.numeric.integer.ByteType > img1 = imgFactory.createImage( dim );
		factory.disableWrapping();

		return img1;
	}

	/**
	 * wraps an ImgLib2 {@link CellImg} of UnsignedByteType (has to be CellImg) into an ImgLib1 {@link Image}
	 *
	 * @param image - input image
	 * @return
	 */
	public static Image< mpicbg.imglib.type.numeric.integer.UnsignedByteType > wrapCellUnsignedByteToImgLib1 ( final Img< UnsignedByteType > img )
	{
		// extract Byte[] arrays from the CellImg consisting of default cells
		@SuppressWarnings( "unchecked" )
		final CellImg< UnsignedByteType, ByteArray > cellImg = (CellImg< UnsignedByteType, ByteArray > )img;
		final ListImg< Cell< ByteArray > > cells = cellImg.getCells();

		final ArrayList< byte[] > celldata = new ArrayList<>( );
		final Cursor< Cell< ByteArray > > cursor = cells.cursor();

		while ( cursor.hasNext() )
			celldata.add( cursor.next().getData().getCurrentStorageArray() );

		// get the cell size and image size
		final int[] cellSize = new int[ img.numDimensions() ];
		cellImg.getCellGrid().cellDimensions( cellSize );
		final int dim[] = Util.long2int( cellImg.getCellGrid().getImgDimensions() );

		// instantiate the new cell container on imglib1
		final PredefinedCellContainerFactory factory = new PredefinedCellContainerFactory( cellSize, new ExistingByteArrays( celldata ) );
		final ImageFactory< mpicbg.imglib.type.numeric.integer.UnsignedByteType > imgFactory =
				new ImageFactory<>( new mpicbg.imglib.type.numeric.integer.UnsignedByteType(), factory );

		final Image< mpicbg.imglib.type.numeric.integer.UnsignedByteType > img1 = imgFactory.createImage( dim );
		factory.disableWrapping();

		return img1;
	}

	/**
	 * wraps an ImgLib2 {@link ArrayImg} of FloatType (has to be array) into an ImgLib1 {@link Image}
	 *
	 * @param image - input image
	 * @return
	 */
	public static Image< mpicbg.imglib.type.numeric.real.FloatType > wrapArrayFloatToImgLib1 ( final Img< FloatType > img )
	{
		// extract float[] array
		@SuppressWarnings( "unchecked" )
		final ArrayImg< FloatType, FloatAccess > array = (ArrayImg< FloatType, FloatAccess >)img;
		final FloatArray f = (FloatArray)array.update( null );
		final float[] data = f.getCurrentStorageArray();

		// convert coordinates
		final int dim[] = new int[ img.numDimensions() ];
		for ( int d = 0; d < dim.length; ++d )
			dim[ d ] = (int)img.dimension( d );

		// create ImgLib1 Array
		final mpicbg.imglib.container.basictypecontainer.FloatAccess access = new mpicbg.imglib.container.basictypecontainer.array.FloatArray( data );
		final Array<mpicbg.imglib.type.numeric.real.FloatType, mpicbg.imglib.container.basictypecontainer.FloatAccess> arrayImgLib1 =
			new Array<mpicbg.imglib.type.numeric.real.FloatType, mpicbg.imglib.container.basictypecontainer.FloatAccess>(
					new ArrayContainerFactory(), access, dim, 1 );

		// create a Type that is linked to the container
		final mpicbg.imglib.type.numeric.real.FloatType linkedType = new mpicbg.imglib.type.numeric.real.FloatType( arrayImgLib1 );

		// pass it to the DirectAccessContainer
		arrayImgLib1.setLinkedType( linkedType );

		return  new Image<mpicbg.imglib.type.numeric.real.FloatType> (arrayImgLib1, new mpicbg.imglib.type.numeric.real.FloatType() );
	}

	/**
	 * wraps an ImgLib2 {@link ArrayImg} of DoubleType (has to be array) into an ImgLib1 {@link Image}
	 *
	 * @param image - input image
	 * @return
	 */
	public static Image< mpicbg.imglib.type.numeric.real.DoubleType > wrapArrayDoubleToImgLib1 ( final Img< DoubleType > img )
	{
		// extract Double[] array
		@SuppressWarnings( "unchecked" )
		final ArrayImg< DoubleType, DoubleAccess > array = (ArrayImg< DoubleType, DoubleAccess >)img;
		final DoubleArray f = (DoubleArray)array.update( null );
		final double[] data = f.getCurrentStorageArray();

		// convert coordinates
		final int dim[] = new int[ img.numDimensions() ];
		for ( int d = 0; d < dim.length; ++d )
			dim[ d ] = (int)img.dimension( d );

		// create ImgLib1 Array
		final mpicbg.imglib.container.basictypecontainer.DoubleAccess access = new mpicbg.imglib.container.basictypecontainer.array.DoubleArray( data );
		final Array<mpicbg.imglib.type.numeric.real.DoubleType, mpicbg.imglib.container.basictypecontainer.DoubleAccess> arrayImgLib1 =
			new Array<mpicbg.imglib.type.numeric.real.DoubleType, mpicbg.imglib.container.basictypecontainer.DoubleAccess>(
					new ArrayContainerFactory(), access, dim, 1 );

		// create a Type that is linked to the container
		final mpicbg.imglib.type.numeric.real.DoubleType linkedType = new mpicbg.imglib.type.numeric.real.DoubleType( arrayImgLib1 );

		// pass it to the DirectAccessContainer
		arrayImgLib1.setLinkedType( linkedType );

		return  new Image<mpicbg.imglib.type.numeric.real.DoubleType> (arrayImgLib1, new mpicbg.imglib.type.numeric.real.DoubleType() );
	}

	/**
	 * wraps an ImgLib2 {@link ArrayImg} of LongType (has to be array) into an ImgLib1 {@link Image}
	 *
	 * @param image - input image
	 * @return
	 */
	public static Image< mpicbg.imglib.type.numeric.integer.LongType > wrapArrayLongToImgLib1 ( final Img< LongType > img )
	{
		// extract Long[] array
		@SuppressWarnings( "unchecked" )
		final ArrayImg< LongType, LongAccess > array = (ArrayImg< LongType, LongAccess >)img;
		final LongArray f = (LongArray)array.update( null );
		final long[] data = f.getCurrentStorageArray();

		// convert coordinates
		final int dim[] = new int[ img.numDimensions() ];
		for ( int d = 0; d < dim.length; ++d )
			dim[ d ] = (int)img.dimension( d );

		// create ImgLib1 Array
		final mpicbg.imglib.container.basictypecontainer.LongAccess access = new mpicbg.imglib.container.basictypecontainer.array.LongArray( data );
		final Array<mpicbg.imglib.type.numeric.integer.LongType, mpicbg.imglib.container.basictypecontainer.LongAccess> arrayImgLib1 =
			new Array<mpicbg.imglib.type.numeric.integer.LongType, mpicbg.imglib.container.basictypecontainer.LongAccess>(
					new ArrayContainerFactory(), access, dim, 1 );

		// create a Type that is linked to the container
		final mpicbg.imglib.type.numeric.integer.LongType linkedType = new mpicbg.imglib.type.numeric.integer.LongType( arrayImgLib1 );

		// pass it to the DirectAccessContainer
		arrayImgLib1.setLinkedType( linkedType );

		return  new Image<mpicbg.imglib.type.numeric.integer.LongType> (arrayImgLib1, new mpicbg.imglib.type.numeric.integer.LongType() );
	}

	/**
	 * wraps an ImgLib2 {@link ArrayImg} of IntType (has to be array) into an ImgLib1 {@link Image}
	 *
	 * @param image - input image
	 * @return
	 */
	public static Image< mpicbg.imglib.type.numeric.integer.IntType > wrapArrayIntToImgLib1 ( final Img< IntType > img )
	{
		// extract Int[] array
		@SuppressWarnings( "unchecked" )
		final ArrayImg< IntType, IntAccess > array = (ArrayImg< IntType, IntAccess >)img;
		final IntArray f = (IntArray)array.update( null );
		final int[] data = f.getCurrentStorageArray();

		// convert coordinates
		final int dim[] = new int[ img.numDimensions() ];
		for ( int d = 0; d < dim.length; ++d )
			dim[ d ] = (int)img.dimension( d );

		// create ImgLib1 Array
		final mpicbg.imglib.container.basictypecontainer.IntAccess access = new mpicbg.imglib.container.basictypecontainer.array.IntArray( data );
		final Array<mpicbg.imglib.type.numeric.integer.IntType, mpicbg.imglib.container.basictypecontainer.IntAccess> arrayImgLib1 =
			new Array<mpicbg.imglib.type.numeric.integer.IntType, mpicbg.imglib.container.basictypecontainer.IntAccess>(
					new ArrayContainerFactory(), access, dim, 1 );

		// create a Type that is linked to the container
		final mpicbg.imglib.type.numeric.integer.IntType linkedType = new mpicbg.imglib.type.numeric.integer.IntType( arrayImgLib1 );

		// pass it to the DirectAccessContainer
		arrayImgLib1.setLinkedType( linkedType );

		return  new Image<mpicbg.imglib.type.numeric.integer.IntType> (arrayImgLib1, new mpicbg.imglib.type.numeric.integer.IntType() );
	}

	/**
	 * wraps an ImgLib2 {@link ArrayImg} of UnsignedIntType (has to be array) into an ImgLib1 {@link Image}
	 *
	 * @param image - input image
	 * @return
	 */
	public static Image< mpicbg.imglib.type.numeric.integer.UnsignedIntType > wrapArrayUnsignedIntToImgLib1 ( final Img< UnsignedIntType > img )
	{
		// extract Int[] array
		@SuppressWarnings( "unchecked" )
		final ArrayImg< UnsignedIntType, IntAccess > array = (ArrayImg< UnsignedIntType, IntAccess >)img;
		final IntArray f = (IntArray)array.update( null );
		final int[] data = f.getCurrentStorageArray();

		// convert coordinates
		final int dim[] = new int[ img.numDimensions() ];
		for ( int d = 0; d < dim.length; ++d )
			dim[ d ] = (int)img.dimension( d );

		// create ImgLib1 Array
		final mpicbg.imglib.container.basictypecontainer.IntAccess access = new mpicbg.imglib.container.basictypecontainer.array.IntArray( data );
		final Array<mpicbg.imglib.type.numeric.integer.UnsignedIntType, mpicbg.imglib.container.basictypecontainer.IntAccess> arrayImgLib1 =
			new Array<mpicbg.imglib.type.numeric.integer.UnsignedIntType, mpicbg.imglib.container.basictypecontainer.IntAccess>(
					new ArrayContainerFactory(), access, dim, 1 );

		// create a Type that is linked to the container
		final mpicbg.imglib.type.numeric.integer.UnsignedIntType linkedType = new mpicbg.imglib.type.numeric.integer.UnsignedIntType( arrayImgLib1 );

		// pass it to the DirectAccessContainer
		arrayImgLib1.setLinkedType( linkedType );

		return  new Image<mpicbg.imglib.type.numeric.integer.UnsignedIntType> (arrayImgLib1, new mpicbg.imglib.type.numeric.integer.UnsignedIntType() );
	}

	/**
	 * wraps an ImgLib2 {@link ArrayImg} of ShortType (has to be array) into an ImgLib1 {@link Image}
	 *
	 * @param image - input image
	 * @return
	 */
	public static Image< mpicbg.imglib.type.numeric.integer.ShortType > wrapArrayShortToImgLib1 ( final Img< ShortType > img )
	{
		// extract Short[] array
		@SuppressWarnings( "unchecked" )
		final ArrayImg< ShortType, ShortAccess > array = (ArrayImg< ShortType, ShortAccess >)img;
		final ShortArray f = (ShortArray)array.update( null );
		final short[] data = f.getCurrentStorageArray();

		// convert coordinates
		final int dim[] = new int[ img.numDimensions() ];
		for ( int d = 0; d < dim.length; ++d )
			dim[ d ] = (int)img.dimension( d );

		// create ImgLib1 Array
		final mpicbg.imglib.container.basictypecontainer.ShortAccess access = new mpicbg.imglib.container.basictypecontainer.array.ShortArray( data );
		final Array<mpicbg.imglib.type.numeric.integer.ShortType, mpicbg.imglib.container.basictypecontainer.ShortAccess> arrayImgLib1 =
			new Array<mpicbg.imglib.type.numeric.integer.ShortType, mpicbg.imglib.container.basictypecontainer.ShortAccess>(
					new ArrayContainerFactory(), access, dim, 1 );

		// create a Type that is linked to the container
		final mpicbg.imglib.type.numeric.integer.ShortType linkedType = new mpicbg.imglib.type.numeric.integer.ShortType( arrayImgLib1 );

		// pass it to the DirectAccessContainer
		arrayImgLib1.setLinkedType( linkedType );

		return  new Image<mpicbg.imglib.type.numeric.integer.ShortType> (arrayImgLib1, new mpicbg.imglib.type.numeric.integer.ShortType() );
	}

	/**
	 * wraps an ImgLib2 {@link ArrayImg} of UnsignedShortType (has to be array) into an ImgLib1 {@link Image}
	 *
	 * @param image - input image
	 * @return
	 */
	public static Image< mpicbg.imglib.type.numeric.integer.UnsignedShortType > wrapArrayUnsignedShortToImgLib1 ( final Img< UnsignedShortType > img )
	{
		// extract Short[] array
		@SuppressWarnings( "unchecked" )
		final ArrayImg< UnsignedShortType, ShortAccess > array = (ArrayImg< UnsignedShortType, ShortAccess >)img;
		final ShortArray f = (ShortArray)array.update( null );
		final short[] data = f.getCurrentStorageArray();

		// convert coordinates
		final int dim[] = new int[ img.numDimensions() ];
		for ( int d = 0; d < dim.length; ++d )
			dim[ d ] = (int)img.dimension( d );

		// create ImgLib1 Array
		final mpicbg.imglib.container.basictypecontainer.ShortAccess access = new mpicbg.imglib.container.basictypecontainer.array.ShortArray( data );
		final Array<mpicbg.imglib.type.numeric.integer.UnsignedShortType, mpicbg.imglib.container.basictypecontainer.ShortAccess> arrayImgLib1 =
			new Array<mpicbg.imglib.type.numeric.integer.UnsignedShortType, mpicbg.imglib.container.basictypecontainer.ShortAccess>(
					new ArrayContainerFactory(), access, dim, 1 );

		// create a Type that is linked to the container
		final mpicbg.imglib.type.numeric.integer.UnsignedShortType linkedType = new mpicbg.imglib.type.numeric.integer.UnsignedShortType( arrayImgLib1 );

		// pass it to the DirectAccessContainer
		arrayImgLib1.setLinkedType( linkedType );

		return  new Image<mpicbg.imglib.type.numeric.integer.UnsignedShortType> (arrayImgLib1, new mpicbg.imglib.type.numeric.integer.UnsignedShortType() );
	}

	/**
	 * wraps an ImgLib2 {@link ArrayImg} of ByteType (has to be array) into an ImgLib1 {@link Image}
	 *
	 * @param image - input image
	 * @return
	 */
	public static Image< mpicbg.imglib.type.numeric.integer.ByteType > wrapArrayByteToImgLib1 ( final Img< ByteType > img )
	{
		// extract Byte[] array
		@SuppressWarnings( "unchecked" )
		final ArrayImg< ByteType, ByteAccess > array = (ArrayImg< ByteType, ByteAccess >)img;
		final ByteArray f = (ByteArray)array.update( null );
		final byte[] data = f.getCurrentStorageArray();

		// convert coordinates
		final int dim[] = new int[ img.numDimensions() ];
		for ( int d = 0; d < dim.length; ++d )
			dim[ d ] = (int)img.dimension( d );

		// create ImgLib1 Array
		final mpicbg.imglib.container.basictypecontainer.ByteAccess access = new mpicbg.imglib.container.basictypecontainer.array.ByteArray( data );
		final Array<mpicbg.imglib.type.numeric.integer.ByteType, mpicbg.imglib.container.basictypecontainer.ByteAccess> arrayImgLib1 =
			new Array<mpicbg.imglib.type.numeric.integer.ByteType, mpicbg.imglib.container.basictypecontainer.ByteAccess>(
					new ArrayContainerFactory(), access, dim, 1 );

		// create a Type that is linked to the container
		final mpicbg.imglib.type.numeric.integer.ByteType linkedType = new mpicbg.imglib.type.numeric.integer.ByteType( arrayImgLib1 );

		// pass it to the DirectAccessContainer
		arrayImgLib1.setLinkedType( linkedType );

		return  new Image<mpicbg.imglib.type.numeric.integer.ByteType> (arrayImgLib1, new mpicbg.imglib.type.numeric.integer.ByteType() );
	}

	/**
	 * wraps an ImgLib2 {@link ArrayImg} of UnsignedByteType (has to be array) into an ImgLib1 {@link Image}
	 *
	 * @param image - input image
	 * @return
	 */
	public static Image< mpicbg.imglib.type.numeric.integer.UnsignedByteType > wrapArrayUnsignedByteToImgLib1 ( final Img< UnsignedByteType > img )
	{
		// extract Byte[] array
		@SuppressWarnings( "unchecked" )
		final ArrayImg< UnsignedByteType, ByteAccess > array = (ArrayImg< UnsignedByteType, ByteAccess >)img;
		final ByteArray f = (ByteArray)array.update( null );
		final byte[] data = f.getCurrentStorageArray();

		// convert coordinates
		final int dim[] = new int[ img.numDimensions() ];
		for ( int d = 0; d < dim.length; ++d )
			dim[ d ] = (int)img.dimension( d );

		// create ImgLib1 Array
		final mpicbg.imglib.container.basictypecontainer.ByteAccess access = new mpicbg.imglib.container.basictypecontainer.array.ByteArray( data );
		final Array<mpicbg.imglib.type.numeric.integer.UnsignedByteType, mpicbg.imglib.container.basictypecontainer.ByteAccess> arrayImgLib1 =
			new Array<mpicbg.imglib.type.numeric.integer.UnsignedByteType, mpicbg.imglib.container.basictypecontainer.ByteAccess>(
					new ArrayContainerFactory(), access, dim, 1 );

		// create a Type that is linked to the container
		final mpicbg.imglib.type.numeric.integer.UnsignedByteType linkedType = new mpicbg.imglib.type.numeric.integer.UnsignedByteType( arrayImgLib1 );

		// pass it to the DirectAccessContainer
		arrayImgLib1.setLinkedType( linkedType );

		return  new Image<mpicbg.imglib.type.numeric.integer.UnsignedByteType> (arrayImgLib1, new mpicbg.imglib.type.numeric.integer.UnsignedByteType() );
	}

	public static void main( final String[] args )
	{
		ImgFactory< FloatType > f;
		f = new CellImgFactory<FloatType>( 5 );
		f = new ArrayImgFactory<FloatType>();

		new ImageJ();

		final Img<FloatType> img = f.create( new long[]{ 19, 8, 3 }, new FloatType() );

		int i = 0;

		for ( final FloatType ft : img )
			ft.set( i++ );

		net.imglib2.img.display.imagej.ImageJFunctions.show( img );
		ImageJFunctions.show( wrapFloatToImgLib1( img ) );

	}
}
