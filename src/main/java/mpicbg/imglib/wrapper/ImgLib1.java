package mpicbg.imglib.wrapper;

import java.util.ArrayList;

import ij.ImageJ;
import mpicbg.imglib.container.ContainerFactory;
import mpicbg.imglib.container.array.Array;
import mpicbg.imglib.container.array.ArrayContainerFactory;
import mpicbg.imglib.container.basictypecontainer.ByteAccess;
import mpicbg.imglib.container.basictypecontainer.DoubleAccess;
import mpicbg.imglib.container.basictypecontainer.FloatAccess;
import mpicbg.imglib.container.basictypecontainer.IntAccess;
import mpicbg.imglib.container.basictypecontainer.LongAccess;
import mpicbg.imglib.container.basictypecontainer.ShortAccess;
import mpicbg.imglib.container.basictypecontainer.array.ByteArray;
import mpicbg.imglib.container.basictypecontainer.array.DoubleArray;
import mpicbg.imglib.container.basictypecontainer.array.FloatArray;
import mpicbg.imglib.container.basictypecontainer.array.IntArray;
import mpicbg.imglib.container.basictypecontainer.array.LongArray;
import mpicbg.imglib.container.basictypecontainer.array.ShortArray;
import mpicbg.imglib.container.cell.CellContainer;
import mpicbg.imglib.container.cell.CellContainerFactory;
import mpicbg.imglib.image.Image;
import mpicbg.imglib.image.ImageFactory;
import mpicbg.imglib.image.display.imagej.ImageJFunctions;
import mpicbg.imglib.type.numeric.integer.ByteType;
import mpicbg.imglib.type.numeric.integer.IntType;
import mpicbg.imglib.type.numeric.integer.LongType;
import mpicbg.imglib.type.numeric.integer.ShortType;
import mpicbg.imglib.type.numeric.integer.UnsignedByteType;
import mpicbg.imglib.type.numeric.integer.UnsignedIntType;
import mpicbg.imglib.type.numeric.integer.UnsignedShortType;
import mpicbg.imglib.type.numeric.real.DoubleType;
import mpicbg.imglib.type.numeric.real.FloatType;
import net.imglib2.img.Img;
import net.imglib2.img.array.ArrayImg;
import net.imglib2.img.cell.Cell;
import net.imglib2.img.cell.CellGrid;
import net.imglib2.img.cell.CellImg;
import net.imglib2.img.cell.CellImgFactory;
import net.imglib2.img.list.ListImg;
import net.imglib2.util.Fraction;
import net.imglib2.util.Util;

/**
 * Provides non-copying wrapping from Imglib1 to Imglib2 (cell, array)
 *
 * @author Stephan Preibisch (stephan.preibisch@gmx.de)
 */
public class ImgLib1
{
	/**
	 * Wrap an ImgLib1 {@link Image} of FloatType into an ImgLib2 {@link Img} (supports Cell and Array)
	 *
	 * @param image - the ImgLib1 input image
	 * @return - the wrapped ImgLib2 image
	 */
	public static Img< net.imglib2.type.numeric.real.FloatType > wrapFloatToImgLib2 ( final Image< FloatType > image )
	{
		if ( image.getContainer() instanceof Array )
			return wrapArrayFloatToImgLib2( image );
		if ( image.getContainer() instanceof CellContainer )
			return wrapCellFloatToImgLib2( image );
		else
			throw new RuntimeException( "Container " + image.getContainer().getClass().getCanonicalName() + " not supported." );
	}

	/**
	 * Wrap an ImgLib1 {@link Image} of DoubleType into an ImgLib2 {@link Img} (supports Cell and Array)
	 *
	 * @param image - the ImgLib1 input image
	 * @return - the wrapped ImgLib2 image
	 */
	public static Img< net.imglib2.type.numeric.real.DoubleType > wrapDoubleToImgLib2 ( final Image< DoubleType > image )
	{
		if ( image.getContainer() instanceof Array )
			return wrapArrayDoubleToImgLib2( image );
		if ( image.getContainer() instanceof CellContainer )
			return wrapCellDoubleToImgLib2( image );
		else
			throw new RuntimeException( "Container " + image.getContainer().getClass().getCanonicalName() + " not supported." );
	}

	/**
	 * Wrap an ImgLib1 {@link Image} of LongType into an ImgLib2 {@link Img} (supports Cell and Array)
	 *
	 * @param image - the ImgLib1 input image
	 * @return - the wrapped ImgLib2 image
	 */
	public static Img< net.imglib2.type.numeric.integer.LongType > wrapLongToImgLib2 ( final Image< LongType > image )
	{
		if ( image.getContainer() instanceof Array )
			return wrapArrayLongToImgLib2( image );
		if ( image.getContainer() instanceof CellContainer )
			return wrapCellLongToImgLib2( image );
		else
			throw new RuntimeException( "Container " + image.getContainer().getClass().getCanonicalName() + " not supported." );
	}

	/**
	 * Wrap an ImgLib1 {@link Image} of IntType into an ImgLib2 {@link Img} (supports Cell and Array)
	 *
	 * @param image - the ImgLib1 input image
	 * @return - the wrapped ImgLib2 image
	 */
	public static Img< net.imglib2.type.numeric.integer.IntType > wrapIntToImgLib2 ( final Image< IntType > image )
	{
		if ( image.getContainer() instanceof Array )
			return wrapArrayIntToImgLib2( image );
		if ( image.getContainer() instanceof CellContainer )
			return wrapCellIntToImgLib2( image );
		else
			throw new RuntimeException( "Container " + image.getContainer().getClass().getCanonicalName() + " not supported." );
	}

	/**
	 * Wrap an ImgLib1 {@link Image} of UnsignedIntType into an ImgLib2 {@link Img} (supports Cell and Array)
	 *
	 * @param image - the ImgLib1 input image
	 * @return - the wrapped ImgLib2 image
	 */
	public static Img< net.imglib2.type.numeric.integer.UnsignedIntType > wrapUnsignedIntToImgLib2 ( final Image< UnsignedIntType > image )
	{
		if ( image.getContainer() instanceof Array )
			return wrapArrayUnsignedIntToImgLib2( image );
		if ( image.getContainer() instanceof CellContainer )
			return wrapCellUnsignedIntToImgLib2( image );
		else
			throw new RuntimeException( "Container " + image.getContainer().getClass().getCanonicalName() + " not supported." );
	}

	/**
	 * Wrap an ImgLib1 {@link Image} of ShortType into an ImgLib2 {@link Img} (supports Cell and Array)
	 *
	 * @param image - the ImgLib1 input image
	 * @return - the wrapped ImgLib2 image
	 */
	public static Img< net.imglib2.type.numeric.integer.ShortType > wrapShortToImgLib2 ( final Image< ShortType > image )
	{
		if ( image.getContainer() instanceof Array )
			return wrapArrayShortToImgLib2( image );
		if ( image.getContainer() instanceof CellContainer )
			return wrapCellShortToImgLib2( image );
		else
			throw new RuntimeException( "Container " + image.getContainer().getClass().getCanonicalName() + " not supported." );
	}

	/**
	 * Wrap an ImgLib1 {@link Image} of UnsignedShortType into an ImgLib2 {@link Img} (supports Cell and Array)
	 *
	 * @param image - the ImgLib1 input image
	 * @return - the wrapped ImgLib2 image
	 */
	public static Img< net.imglib2.type.numeric.integer.UnsignedShortType > wrapUnsignedShortToImgLib2 ( final Image< UnsignedShortType > image )
	{
		if ( image.getContainer() instanceof Array )
			return wrapArrayUnsignedShortToImgLib2( image );
		if ( image.getContainer() instanceof CellContainer )
			return wrapCellUnsignedShortToImgLib2( image );
		else
			throw new RuntimeException( "Container " + image.getContainer().getClass().getCanonicalName() + " not supported." );
	}

	/**
	 * Wrap an ImgLib1 {@link Image} of ByteType into an ImgLib2 {@link Img} (supports Cell and Array)
	 *
	 * @param image - the ImgLib1 input image
	 * @return - the wrapped ImgLib2 image
	 */
	public static Img< net.imglib2.type.numeric.integer.ByteType > wrapByteToImgLib2 ( final Image< ByteType > image )
	{
		if ( image.getContainer() instanceof Array )
			return wrapArrayByteToImgLib2( image );
		if ( image.getContainer() instanceof CellContainer )
			return wrapCellByteToImgLib2( image );
		else
			throw new RuntimeException( "Container " + image.getContainer().getClass().getCanonicalName() + " not supported." );
	}

	/**
	 * Wrap an ImgLib1 {@link Image} of UnsignedByteType into an ImgLib2 {@link Img} (supports Cell and Array)
	 *
	 * @param image - the ImgLib1 input image
	 * @return - the wrapped ImgLib2 image
	 */
	public static Img< net.imglib2.type.numeric.integer.UnsignedByteType > wrapUnsignedByteToImgLib2 ( final Image< UnsignedByteType > image )
	{
		if ( image.getContainer() instanceof Array )
			return wrapArrayUnsignedByteToImgLib2( image );
		if ( image.getContainer() instanceof CellContainer )
			return wrapCellUnsignedByteToImgLib2( image );
		else
			throw new RuntimeException( "Container " + image.getContainer().getClass().getCanonicalName() + " not supported." );
	}

	/**
	 * wrapArrays an ImgLib1 {@link Image} of FloatType (has to be cell) into an ImgLib2 {@link Img} using a {@link CellImg}
	 *
	 * @param image - input image
	 * @return
	 */
	public static Img< net.imglib2.type.numeric.real.FloatType > wrapCellFloatToImgLib2 ( final Image< FloatType > image )
	{
		// extract float[] arrays
		@SuppressWarnings( "unchecked" )
		final CellContainer< FloatType, FloatArray > cellContainer = (CellContainer< FloatType, FloatArray >)image.getContainer();

		final int n = cellContainer.getNumDimensions();
		final long[] dimensions = Util.int2long( cellContainer.getDimensions() );
		final int[] cellDimensions = cellContainer.getCellSize();
		final CellGrid grid = new CellGrid( dimensions, cellDimensions );

		final ArrayList< Cell< net.imglib2.img.basictypeaccess.array.FloatArray > > cellList = new ArrayList<>();
		final long[] cellMin = new long[ n ];
		final int[] cellDims = new int[ n ];
		for ( int i = 0; i < cellContainer.getNumCells(); ++i )
		{
			grid.getCellDimensions( i, cellMin, cellDims );
			cellList.add( new Cell<>( cellDims, cellMin, new net.imglib2.img.basictypeaccess.array.FloatArray(
					cellContainer.getCell( i ).getData().getCurrentStorageArray() ) ) );
		}

		// create ImgLib2 CellImg
		final ListImg< Cell< net.imglib2.img.basictypeaccess.array.FloatArray > > cells = new ListImg<>( cellList, grid.getGridDimensions() );
		final CellImgFactory< net.imglib2.type.numeric.real.FloatType > factory = new CellImgFactory<>( cellDimensions );
		final Fraction entitiesPerPixel = new net.imglib2.type.numeric.real.FloatType().getEntitiesPerPixel();
		final CellImg< net.imglib2.type.numeric.real.FloatType, net.imglib2.img.basictypeaccess.array.FloatArray > cellImg = new CellImg<>( factory, grid, cells, entitiesPerPixel );

		// create a Type that is linked to the container
		final net.imglib2.type.numeric.real.FloatType linkedType = new net.imglib2.type.numeric.real.FloatType( cellImg );

		// pass it to the NativeContainer
		cellImg.setLinkedType( linkedType );

		return cellImg;
	}

	/**
	 * wrapArrays an ImgLib1 {@link Image} of DoubleType (has to be cell) into an ImgLib2 {@link Img} using a {@link CellImg}
	 *
	 * @param image - input image
	 * @return
	 */
	public static Img< net.imglib2.type.numeric.real.DoubleType > wrapCellDoubleToImgLib2 ( final Image< DoubleType > image )
	{
		// extract Double[] arrays
		@SuppressWarnings( "unchecked" )
		final CellContainer< DoubleType, DoubleArray > cellContainer = (CellContainer< DoubleType, DoubleArray >)image.getContainer();

		final int n = cellContainer.getNumDimensions();
		final long[] dimensions = Util.int2long( cellContainer.getDimensions() );
		final int[] cellDimensions = cellContainer.getCellSize();
		final CellGrid grid = new CellGrid( dimensions, cellDimensions );

		final ArrayList< Cell< net.imglib2.img.basictypeaccess.array.DoubleArray > > cellList = new ArrayList<>();
		final long[] cellMin = new long[ n ];
		final int[] cellDims = new int[ n ];
		for ( int i = 0; i < cellContainer.getNumCells(); ++i )
		{
			grid.getCellDimensions( i, cellMin, cellDims );
			cellList.add( new Cell<>( cellDims, cellMin, new net.imglib2.img.basictypeaccess.array.DoubleArray(
					cellContainer.getCell( i ).getData().getCurrentStorageArray() ) ) );
		}

		// create ImgLib2 CellImg
		final ListImg< Cell< net.imglib2.img.basictypeaccess.array.DoubleArray > > cells = new ListImg<>( cellList, grid.getGridDimensions() );
		final CellImgFactory< net.imglib2.type.numeric.real.DoubleType > factory = new CellImgFactory<>( cellDimensions );
		final Fraction entitiesPerPixel = new net.imglib2.type.numeric.real.DoubleType().getEntitiesPerPixel();
		final CellImg< net.imglib2.type.numeric.real.DoubleType, net.imglib2.img.basictypeaccess.array.DoubleArray > cellImg = new CellImg<>( factory, grid, cells, entitiesPerPixel );

		// create a Type that is linked to the container
		final net.imglib2.type.numeric.real.DoubleType linkedType = new net.imglib2.type.numeric.real.DoubleType( cellImg );

		// pass it to the NativeContainer
		cellImg.setLinkedType( linkedType );

		return cellImg;
	}

	/**
	 * wrapArrays an ImgLib1 {@link Image} of LongType (has to be cell) into an ImgLib2 {@link Img} using a {@link CellImg}
	 *
	 * @param image - input image
	 * @return
	 */
	public static Img< net.imglib2.type.numeric.integer.LongType > wrapCellLongToImgLib2 ( final Image< LongType > image )
	{
		// extract Long[] arrays
		@SuppressWarnings( "unchecked" )
		final CellContainer< LongType, LongArray > cellContainer = (CellContainer< LongType, LongArray >)image.getContainer();

		final int n = cellContainer.getNumDimensions();
		final long[] dimensions = Util.int2long( cellContainer.getDimensions() );
		final int[] cellDimensions = cellContainer.getCellSize();
		final CellGrid grid = new CellGrid( dimensions, cellDimensions );

		final ArrayList< Cell< net.imglib2.img.basictypeaccess.array.LongArray > > cellList = new ArrayList<>();
		final long[] cellMin = new long[ n ];
		final int[] cellDims = new int[ n ];
		for ( int i = 0; i < cellContainer.getNumCells(); ++i )
		{
			grid.getCellDimensions( i, cellMin, cellDims );
			cellList.add( new Cell<>( cellDims, cellMin, new net.imglib2.img.basictypeaccess.array.LongArray(
					cellContainer.getCell( i ).getData().getCurrentStorageArray() ) ) );
		}

		// create ImgLib2 CellImg
		final ListImg< Cell< net.imglib2.img.basictypeaccess.array.LongArray > > cells = new ListImg<>( cellList, grid.getGridDimensions() );
		final CellImgFactory< net.imglib2.type.numeric.integer.LongType > factory = new CellImgFactory<>( cellDimensions );
		final Fraction entitiesPerPixel = new net.imglib2.type.numeric.integer.LongType().getEntitiesPerPixel();
		final CellImg< net.imglib2.type.numeric.integer.LongType, net.imglib2.img.basictypeaccess.array.LongArray > cellImg = new CellImg<>( factory, grid, cells, entitiesPerPixel );

		// create a Type that is linked to the container
		final net.imglib2.type.numeric.integer.LongType linkedType = new net.imglib2.type.numeric.integer.LongType( cellImg );

		// pass it to the NativeContainer
		cellImg.setLinkedType( linkedType );

		return cellImg;
	}

	/**
	 * wrapArrays an ImgLib1 {@link Image} of IntType (has to be cell) into an ImgLib2 {@link Img} using a {@link CellImg}
	 *
	 * @param image - input image
	 * @return
	 */
	public static Img< net.imglib2.type.numeric.integer.IntType > wrapCellIntToImgLib2 ( final Image< IntType > image )
	{
		// extract Int[] arrays
		@SuppressWarnings( "unchecked" )
		final CellContainer< IntType, IntArray > cellContainer = (CellContainer< IntType, IntArray >)image.getContainer();

		final int n = cellContainer.getNumDimensions();
		final long[] dimensions = Util.int2long( cellContainer.getDimensions() );
		final int[] cellDimensions = cellContainer.getCellSize();
		final CellGrid grid = new CellGrid( dimensions, cellDimensions );

		final ArrayList< Cell< net.imglib2.img.basictypeaccess.array.IntArray > > cellList = new ArrayList<>();
		final long[] cellMin = new long[ n ];
		final int[] cellDims = new int[ n ];
		for ( int i = 0; i < cellContainer.getNumCells(); ++i )
		{
			grid.getCellDimensions( i, cellMin, cellDims );
			cellList.add( new Cell<>( cellDims, cellMin, new net.imglib2.img.basictypeaccess.array.IntArray(
					cellContainer.getCell( i ).getData().getCurrentStorageArray() ) ) );
		}

		// create ImgLib2 CellImg
		final ListImg< Cell< net.imglib2.img.basictypeaccess.array.IntArray > > cells = new ListImg<>( cellList, grid.getGridDimensions() );
		final CellImgFactory< net.imglib2.type.numeric.integer.IntType > factory = new CellImgFactory<>( cellDimensions );
		final Fraction entitiesPerPixel = new net.imglib2.type.numeric.integer.IntType().getEntitiesPerPixel();
		final CellImg< net.imglib2.type.numeric.integer.IntType, net.imglib2.img.basictypeaccess.array.IntArray > cellImg = new CellImg<>( factory, grid, cells, entitiesPerPixel );

		// create a Type that is linked to the container
		final net.imglib2.type.numeric.integer.IntType linkedType = new net.imglib2.type.numeric.integer.IntType( cellImg );

		// pass it to the NativeContainer
		cellImg.setLinkedType( linkedType );

		return cellImg;
	}

	/**
	 * wrapArrays an ImgLib1 {@link Image} of UnsignedIntType (has to be cell) into an ImgLib2 {@link Img} using a {@link CellImg}
	 *
	 * @param image - input image
	 * @return
	 */
	public static Img< net.imglib2.type.numeric.integer.UnsignedIntType > wrapCellUnsignedIntToImgLib2 ( final Image< UnsignedIntType > image )
	{
		// extract Int[] arrays
		@SuppressWarnings( "unchecked" )
		final CellContainer< UnsignedIntType, IntArray > cellContainer = (CellContainer< UnsignedIntType, IntArray >)image.getContainer();

		final int n = cellContainer.getNumDimensions();
		final long[] dimensions = Util.int2long( cellContainer.getDimensions() );
		final int[] cellDimensions = cellContainer.getCellSize();
		final CellGrid grid = new CellGrid( dimensions, cellDimensions );

		final ArrayList< Cell< net.imglib2.img.basictypeaccess.array.IntArray > > cellList = new ArrayList<>();
		final long[] cellMin = new long[ n ];
		final int[] cellDims = new int[ n ];
		for ( int i = 0; i < cellContainer.getNumCells(); ++i )
		{
			grid.getCellDimensions( i, cellMin, cellDims );
			cellList.add( new Cell<>( cellDims, cellMin, new net.imglib2.img.basictypeaccess.array.IntArray(
					cellContainer.getCell( i ).getData().getCurrentStorageArray() ) ) );
		}

		// create ImgLib2 CellImg
		final ListImg< Cell< net.imglib2.img.basictypeaccess.array.IntArray > > cells = new ListImg<>( cellList, grid.getGridDimensions() );
		final CellImgFactory< net.imglib2.type.numeric.integer.UnsignedIntType > factory = new CellImgFactory<>( cellDimensions );
		final Fraction entitiesPerPixel = new net.imglib2.type.numeric.integer.UnsignedIntType().getEntitiesPerPixel();
		final CellImg< net.imglib2.type.numeric.integer.UnsignedIntType, net.imglib2.img.basictypeaccess.array.IntArray > cellImg = new CellImg<>( factory, grid, cells, entitiesPerPixel );

		// create a Type that is linked to the container
		final net.imglib2.type.numeric.integer.UnsignedIntType linkedType = new net.imglib2.type.numeric.integer.UnsignedIntType( cellImg );

		// pass it to the NativeContainer
		cellImg.setLinkedType( linkedType );

		return cellImg;
	}

	/**
	 * wrapArrays an ImgLib1 {@link Image} of ShortType (has to be cell) into an ImgLib2 {@link Img} using a {@link CellImg}
	 *
	 * @param image - input image
	 * @return
	 */
	public static Img< net.imglib2.type.numeric.integer.ShortType > wrapCellShortToImgLib2 ( final Image< ShortType > image )
	{
		// extract Short[] arrays
		@SuppressWarnings( "unchecked" )
		final CellContainer< ShortType, ShortArray > cellContainer = (CellContainer< ShortType, ShortArray >)image.getContainer();

		final int n = cellContainer.getNumDimensions();
		final long[] dimensions = Util.int2long( cellContainer.getDimensions() );
		final int[] cellDimensions = cellContainer.getCellSize();
		final CellGrid grid = new CellGrid( dimensions, cellDimensions );

		final ArrayList< Cell< net.imglib2.img.basictypeaccess.array.ShortArray > > cellList = new ArrayList<>();
		final long[] cellMin = new long[ n ];
		final int[] cellDims = new int[ n ];
		for ( int i = 0; i < cellContainer.getNumCells(); ++i )
		{
			grid.getCellDimensions( i, cellMin, cellDims );
			cellList.add( new Cell<>( cellDims, cellMin, new net.imglib2.img.basictypeaccess.array.ShortArray(
					cellContainer.getCell( i ).getData().getCurrentStorageArray() ) ) );
		}

		// create ImgLib2 CellImg
		final ListImg< Cell< net.imglib2.img.basictypeaccess.array.ShortArray > > cells = new ListImg<>( cellList, grid.getGridDimensions() );
		final CellImgFactory< net.imglib2.type.numeric.integer.ShortType > factory = new CellImgFactory<>( cellDimensions );
		final Fraction entitiesPerPixel = new net.imglib2.type.numeric.integer.ShortType().getEntitiesPerPixel();
		final CellImg< net.imglib2.type.numeric.integer.ShortType, net.imglib2.img.basictypeaccess.array.ShortArray > cellImg = new CellImg<>( factory, grid, cells, entitiesPerPixel );

		// create a Type that is linked to the container
		final net.imglib2.type.numeric.integer.ShortType linkedType = new net.imglib2.type.numeric.integer.ShortType( cellImg );

		// pass it to the NativeContainer
		cellImg.setLinkedType( linkedType );

		return cellImg;
	}

	/**
	 * wrapArrays an ImgLib1 {@link Image} of UnsignedShortType (has to be cell) into an ImgLib2 {@link Img} using a {@link CellImg}
	 *
	 * @param image - input image
	 * @return
	 */
	public static Img< net.imglib2.type.numeric.integer.UnsignedShortType > wrapCellUnsignedShortToImgLib2 ( final Image< UnsignedShortType > image )
	{
		// extract Short[] arrays
		@SuppressWarnings( "unchecked" )
		final CellContainer< UnsignedShortType, ShortArray > cellContainer = (CellContainer< UnsignedShortType, ShortArray >)image.getContainer();

		final int n = cellContainer.getNumDimensions();
		final long[] dimensions = Util.int2long( cellContainer.getDimensions() );
		final int[] cellDimensions = cellContainer.getCellSize();
		final CellGrid grid = new CellGrid( dimensions, cellDimensions );

		final ArrayList< Cell< net.imglib2.img.basictypeaccess.array.ShortArray > > cellList = new ArrayList<>();
		final long[] cellMin = new long[ n ];
		final int[] cellDims = new int[ n ];
		for ( int i = 0; i < cellContainer.getNumCells(); ++i )
		{
			grid.getCellDimensions( i, cellMin, cellDims );
			cellList.add( new Cell<>( cellDims, cellMin, new net.imglib2.img.basictypeaccess.array.ShortArray(
					cellContainer.getCell( i ).getData().getCurrentStorageArray() ) ) );
		}

		// create ImgLib2 CellImg
		final ListImg< Cell< net.imglib2.img.basictypeaccess.array.ShortArray > > cells = new ListImg<>( cellList, grid.getGridDimensions() );
		final CellImgFactory< net.imglib2.type.numeric.integer.UnsignedShortType > factory = new CellImgFactory<>( cellDimensions );
		final Fraction entitiesPerPixel = new net.imglib2.type.numeric.integer.UnsignedShortType().getEntitiesPerPixel();
		final CellImg< net.imglib2.type.numeric.integer.UnsignedShortType, net.imglib2.img.basictypeaccess.array.ShortArray > cellImg = new CellImg<>( factory, grid, cells, entitiesPerPixel );

		// create a Type that is linked to the container
		final net.imglib2.type.numeric.integer.UnsignedShortType linkedType = new net.imglib2.type.numeric.integer.UnsignedShortType( cellImg );

		// pass it to the NativeContainer
		cellImg.setLinkedType( linkedType );

		return cellImg;
	}

	/**
	 * wrapArrays an ImgLib1 {@link Image} of ByteType (has to be cell) into an ImgLib2 {@link Img} using a {@link CellImg}
	 *
	 * @param image - input image
	 * @return
	 */
	public static Img< net.imglib2.type.numeric.integer.ByteType > wrapCellByteToImgLib2 ( final Image< ByteType > image )
	{
		// extract Byte[] arrays
		@SuppressWarnings( "unchecked" )
		final CellContainer< ByteType, ByteArray > cellContainer = (CellContainer< ByteType, ByteArray >)image.getContainer();

		final int n = cellContainer.getNumDimensions();
		final long[] dimensions = Util.int2long( cellContainer.getDimensions() );
		final int[] cellDimensions = cellContainer.getCellSize();
		final CellGrid grid = new CellGrid( dimensions, cellDimensions );

		final ArrayList< Cell< net.imglib2.img.basictypeaccess.array.ByteArray > > cellList = new ArrayList<>();
		final long[] cellMin = new long[ n ];
		final int[] cellDims = new int[ n ];
		for ( int i = 0; i < cellContainer.getNumCells(); ++i )
		{
			grid.getCellDimensions( i, cellMin, cellDims );
			cellList.add( new Cell<>( cellDims, cellMin, new net.imglib2.img.basictypeaccess.array.ByteArray(
					cellContainer.getCell( i ).getData().getCurrentStorageArray() ) ) );
		}

		// create ImgLib2 CellImg
		final ListImg< Cell< net.imglib2.img.basictypeaccess.array.ByteArray > > cells = new ListImg<>( cellList, grid.getGridDimensions() );
		final CellImgFactory< net.imglib2.type.numeric.integer.ByteType > factory = new CellImgFactory<>( cellDimensions );
		final Fraction entitiesPerPixel = new net.imglib2.type.numeric.integer.ByteType().getEntitiesPerPixel();
		final CellImg< net.imglib2.type.numeric.integer.ByteType, net.imglib2.img.basictypeaccess.array.ByteArray > cellImg = new CellImg<>( factory, grid, cells, entitiesPerPixel );

		// create a Type that is linked to the container
		final net.imglib2.type.numeric.integer.ByteType linkedType = new net.imglib2.type.numeric.integer.ByteType( cellImg );

		// pass it to the NativeContainer
		cellImg.setLinkedType( linkedType );

		return cellImg;
	}

	/**
	 * wrapArrays an ImgLib1 {@link Image} of UnsignedByteType (has to be cell) into an ImgLib2 {@link Img} using a {@link CellImg}
	 *
	 * @param image - input image
	 * @return
	 */
	public static Img< net.imglib2.type.numeric.integer.UnsignedByteType > wrapCellUnsignedByteToImgLib2 ( final Image< UnsignedByteType > image )
	{
		// extract Byte[] arrays
		@SuppressWarnings( "unchecked" )
		final CellContainer< UnsignedByteType, ByteArray > cellContainer = (CellContainer< UnsignedByteType, ByteArray >)image.getContainer();

		final int n = cellContainer.getNumDimensions();
		final long[] dimensions = Util.int2long( cellContainer.getDimensions() );
		final int[] cellDimensions = cellContainer.getCellSize();
		final CellGrid grid = new CellGrid( dimensions, cellDimensions );

		final ArrayList< Cell< net.imglib2.img.basictypeaccess.array.ByteArray > > cellList = new ArrayList<>();
		final long[] cellMin = new long[ n ];
		final int[] cellDims = new int[ n ];
		for ( int i = 0; i < cellContainer.getNumCells(); ++i )
		{
			grid.getCellDimensions( i, cellMin, cellDims );
			cellList.add( new Cell<>( cellDims, cellMin, new net.imglib2.img.basictypeaccess.array.ByteArray(
					cellContainer.getCell( i ).getData().getCurrentStorageArray() ) ) );
		}

		// create ImgLib2 CellImg
		final ListImg< Cell< net.imglib2.img.basictypeaccess.array.ByteArray > > cells = new ListImg<>( cellList, grid.getGridDimensions() );
		final CellImgFactory< net.imglib2.type.numeric.integer.UnsignedByteType > factory = new CellImgFactory<>( cellDimensions );
		final Fraction entitiesPerPixel = new net.imglib2.type.numeric.integer.UnsignedByteType().getEntitiesPerPixel();
		final CellImg< net.imglib2.type.numeric.integer.UnsignedByteType, net.imglib2.img.basictypeaccess.array.ByteArray > cellImg = new CellImg<>( factory, grid, cells, entitiesPerPixel );

		// create a Type that is linked to the container
		final net.imglib2.type.numeric.integer.UnsignedByteType linkedType = new net.imglib2.type.numeric.integer.UnsignedByteType( cellImg );

		// pass it to the NativeContainer
		cellImg.setLinkedType( linkedType );

		return cellImg;
	}

	/**
	 * wrapArrays an ImgLib1 {@link Image} of FloatType (has to be array) into an ImgLib2 {@link Img} using an {@link ArrayImg}
	 *
	 * @param image - input image
	 * @return
	 */
	public static Img< net.imglib2.type.numeric.real.FloatType > wrapArrayFloatToImgLib2 ( final Image< FloatType > image )
	{
		// extract float[] array
		@SuppressWarnings( "unchecked" )
		final Array< FloatType, FloatAccess> array = (Array< FloatType, FloatAccess> ) image.getContainer();
		final FloatArray f = (FloatArray)array.update( null );
		final float[] data = f.getCurrentStorageArray();

		// convert coordinates
		final long dim[] = new long[ image.getNumDimensions() ];
		for ( int d = 0; d < dim.length; ++d )
			dim[ d ] = image.getDimension( d );

		// create ImgLib2 Array
		final net.imglib2.img.basictypeaccess.FloatAccess access = new net.imglib2.img.basictypeaccess.array.FloatArray( data );
		final ArrayImg< net.imglib2.type.numeric.real.FloatType, net.imglib2.img.basictypeaccess.FloatAccess> arrayImgLib2 =
			new ArrayImg< net.imglib2.type.numeric.real.FloatType, net.imglib2.img.basictypeaccess.FloatAccess>( access, dim, new Fraction() );

		// create a Type that is linked to the container
		final net.imglib2.type.numeric.real.FloatType linkedType = new net.imglib2.type.numeric.real.FloatType( arrayImgLib2 );

		// pass it to the DirectAccessContainer
		arrayImgLib2.setLinkedType( linkedType );

		return arrayImgLib2;
	}

	/**
	 * wrapArrays an ImgLib1 {@link Image} of DoubleType (has to be array) into an ImgLib2 {@link Img} using an {@link ArrayImg}
	 *
	 * @param image - input image
	 * @return
	 */
	public static Img< net.imglib2.type.numeric.real.DoubleType > wrapArrayDoubleToImgLib2 ( final Image< DoubleType > image )
	{
		// extract float[] array
		@SuppressWarnings( "unchecked" )
		final Array< DoubleType, DoubleAccess> array = (Array< DoubleType, DoubleAccess> ) image.getContainer();
		final DoubleArray f = (DoubleArray)array.update( null );
		final double[] data = f.getCurrentStorageArray();

		// convert coordinates
		final long dim[] = new long[ image.getNumDimensions() ];
		for ( int d = 0; d < dim.length; ++d )
			dim[ d ] = image.getDimension( d );

		// create ImgLib2 Array
		final net.imglib2.img.basictypeaccess.DoubleAccess access = new net.imglib2.img.basictypeaccess.array.DoubleArray( data );
		final ArrayImg< net.imglib2.type.numeric.real.DoubleType, net.imglib2.img.basictypeaccess.DoubleAccess> arrayImgLib2 =
			new ArrayImg< net.imglib2.type.numeric.real.DoubleType, net.imglib2.img.basictypeaccess.DoubleAccess>( access, dim, new Fraction() );

		// create a Type that is linked to the container
		final net.imglib2.type.numeric.real.DoubleType linkedType = new net.imglib2.type.numeric.real.DoubleType( arrayImgLib2 );

		// pass it to the DirectAccessContainer
		arrayImgLib2.setLinkedType( linkedType );

		return arrayImgLib2;
	}

	/**
	 * wrapArrays an ImgLib1 {@link Image} of LongType (has to be array) into an ImgLib2 {@link Img} using an {@link ArrayImg}
	 *
	 * @param image - input image
	 * @return
	 */
	public static Img< net.imglib2.type.numeric.integer.LongType > wrapArrayLongToImgLib2 ( final Image< LongType > image )
	{
		// extract Long[] array
		@SuppressWarnings( "unchecked" )
		final Array< LongType, LongAccess> array = (Array< LongType, LongAccess> ) image.getContainer();
		final LongArray f = (LongArray)array.update( null );
		final long[] data = f.getCurrentStorageArray();

		// convert coordinates
		final long dim[] = new long[ image.getNumDimensions() ];
		for ( int d = 0; d < dim.length; ++d )
			dim[ d ] = image.getDimension( d );

		// create ImgLib2 Array
		final net.imglib2.img.basictypeaccess.LongAccess access = new net.imglib2.img.basictypeaccess.array.LongArray( data );
		final ArrayImg< net.imglib2.type.numeric.integer.LongType, net.imglib2.img.basictypeaccess.LongAccess> arrayImgLib2 =
			new ArrayImg< net.imglib2.type.numeric.integer.LongType, net.imglib2.img.basictypeaccess.LongAccess>( access, dim, new Fraction() );

		// create a Type that is linked to the container
		final net.imglib2.type.numeric.integer.LongType linkedType = new net.imglib2.type.numeric.integer.LongType( arrayImgLib2 );

		// pass it to the DirectAccessContainer
		arrayImgLib2.setLinkedType( linkedType );

		return arrayImgLib2;
	}

	/**
	 * wrapArrays an ImgLib1 {@link Image} of UnsignedIntType (has to be array) into an ImgLib2 {@link Img} using an {@link ArrayImg}
	 *
	 * @param image - input image
	 * @return
	 */
	public static Img< net.imglib2.type.numeric.integer.UnsignedIntType > wrapArrayUnsignedIntToImgLib2 ( final Image< UnsignedIntType > image )
	{
		// extract Int[] array
		@SuppressWarnings( "unchecked" )
		final Array< UnsignedIntType, IntAccess> array = (Array< UnsignedIntType, IntAccess> ) image.getContainer();
		final IntArray f = (IntArray)array.update( null );
		final int[] data = f.getCurrentStorageArray();

		// convert coordinates
		final long dim[] = new long[ image.getNumDimensions() ];
		for ( int d = 0; d < dim.length; ++d )
			dim[ d ] = image.getDimension( d );

		// create ImgLib2 Array
		final net.imglib2.img.basictypeaccess.IntAccess access = new net.imglib2.img.basictypeaccess.array.IntArray( data );
		final ArrayImg< net.imglib2.type.numeric.integer.UnsignedIntType, net.imglib2.img.basictypeaccess.IntAccess> arrayImgLib2 =
			new ArrayImg< net.imglib2.type.numeric.integer.UnsignedIntType, net.imglib2.img.basictypeaccess.IntAccess>( access, dim, new Fraction() );

		// create a Type that is linked to the container
		final net.imglib2.type.numeric.integer.UnsignedIntType linkedType = new net.imglib2.type.numeric.integer.UnsignedIntType( arrayImgLib2 );

		// pass it to the DirectAccessContainer
		arrayImgLib2.setLinkedType( linkedType );

		return arrayImgLib2;
	}

	/**
	 * wrapArrays an ImgLib1 {@link Image} of IntType (has to be array) into an ImgLib2 {@link Img} using an {@link ArrayImg}
	 *
	 * @param image - input image
	 * @return
	 */
	public static Img< net.imglib2.type.numeric.integer.IntType > wrapArrayIntToImgLib2 ( final Image< IntType > image )
	{
		// extract Int[] array
		@SuppressWarnings( "unchecked" )
		final Array< IntType, IntAccess> array = (Array< IntType, IntAccess> ) image.getContainer();
		final IntArray f = (IntArray)array.update( null );
		final int[] data = f.getCurrentStorageArray();

		// convert coordinates
		final long dim[] = new long[ image.getNumDimensions() ];
		for ( int d = 0; d < dim.length; ++d )
			dim[ d ] = image.getDimension( d );

		// create ImgLib2 Array
		final net.imglib2.img.basictypeaccess.IntAccess access = new net.imglib2.img.basictypeaccess.array.IntArray( data );
		final ArrayImg< net.imglib2.type.numeric.integer.IntType, net.imglib2.img.basictypeaccess.IntAccess> arrayImgLib2 =
			new ArrayImg< net.imglib2.type.numeric.integer.IntType, net.imglib2.img.basictypeaccess.IntAccess>( access, dim, new Fraction() );

		// create a Type that is linked to the container
		final net.imglib2.type.numeric.integer.IntType linkedType = new net.imglib2.type.numeric.integer.IntType( arrayImgLib2 );

		// pass it to the DirectAccessContainer
		arrayImgLib2.setLinkedType( linkedType );

		return arrayImgLib2;
	}

	/**
	 * wrapArrays an ImgLib1 {@link Image} of UnsignedShortType (has to be array) into an ImgLib2 {@link Img} using an {@link ArrayImg}
	 *
	 * @param image - input image
	 * @return
	 */
	public static Img< net.imglib2.type.numeric.integer.UnsignedShortType > wrapArrayUnsignedShortToImgLib2 ( final Image< UnsignedShortType > image )
	{
		// extract Short[] array
		@SuppressWarnings( "unchecked" )
		final Array< UnsignedShortType, ShortAccess> array = (Array< UnsignedShortType, ShortAccess> ) image.getContainer();
		final ShortArray f = (ShortArray)array.update( null );
		final short[] data = f.getCurrentStorageArray();

		// convert coordinates
		final long dim[] = new long[ image.getNumDimensions() ];
		for ( int d = 0; d < dim.length; ++d )
			dim[ d ] = image.getDimension( d );

		// create ImgLib2 Array
		final net.imglib2.img.basictypeaccess.ShortAccess access = new net.imglib2.img.basictypeaccess.array.ShortArray( data );
		final ArrayImg< net.imglib2.type.numeric.integer.UnsignedShortType, net.imglib2.img.basictypeaccess.ShortAccess> arrayImgLib2 =
			new ArrayImg< net.imglib2.type.numeric.integer.UnsignedShortType, net.imglib2.img.basictypeaccess.ShortAccess>( access, dim, new Fraction() );

		// create a Type that is linked to the container
		final net.imglib2.type.numeric.integer.UnsignedShortType linkedType = new net.imglib2.type.numeric.integer.UnsignedShortType( arrayImgLib2 );

		// pass it to the DirectAccessContainer
		arrayImgLib2.setLinkedType( linkedType );

		return arrayImgLib2;
	}

	/**
	 * wrapArrays an ImgLib1 {@link Image} of ShortType (has to be array) into an ImgLib2 {@link Img} using an {@link ArrayImg}
	 *
	 * @param image - input image
	 * @return
	 */
	public static Img< net.imglib2.type.numeric.integer.ShortType > wrapArrayShortToImgLib2 ( final Image< ShortType > image )
	{
		// extract Short[] array
		@SuppressWarnings( "unchecked" )
		final Array< ShortType, ShortAccess> array = (Array< ShortType, ShortAccess> ) image.getContainer();
		final ShortArray f = (ShortArray)array.update( null );
		final short[] data = f.getCurrentStorageArray();

		// convert coordinates
		final long dim[] = new long[ image.getNumDimensions() ];
		for ( int d = 0; d < dim.length; ++d )
			dim[ d ] = image.getDimension( d );

		// create ImgLib2 Array
		final net.imglib2.img.basictypeaccess.ShortAccess access = new net.imglib2.img.basictypeaccess.array.ShortArray( data );
		final ArrayImg< net.imglib2.type.numeric.integer.ShortType, net.imglib2.img.basictypeaccess.ShortAccess> arrayImgLib2 =
			new ArrayImg< net.imglib2.type.numeric.integer.ShortType, net.imglib2.img.basictypeaccess.ShortAccess>( access, dim, new Fraction() );

		// create a Type that is linked to the container
		final net.imglib2.type.numeric.integer.ShortType linkedType = new net.imglib2.type.numeric.integer.ShortType( arrayImgLib2 );

		// pass it to the DirectAccessContainer
		arrayImgLib2.setLinkedType( linkedType );

		return arrayImgLib2;
	}

	/**
	 * wrapArrays an ImgLib1 {@link Image} of ByteType (has to be array) into an ImgLib2 {@link Img} using an {@link ArrayImg}
	 *
	 * @param image - input image
	 * @return
	 */
	public static Img< net.imglib2.type.numeric.integer.ByteType > wrapArrayByteToImgLib2 ( final Image< ByteType > image )
	{
		// extract Byte[] array
		@SuppressWarnings( "unchecked" )
		final Array< ByteType, ByteAccess> array = (Array< ByteType, ByteAccess> ) image.getContainer();
		final ByteArray f = (ByteArray)array.update( null );
		final byte[] data = f.getCurrentStorageArray();

		// convert coordinates
		final long dim[] = new long[ image.getNumDimensions() ];
		for ( int d = 0; d < dim.length; ++d )
			dim[ d ] = image.getDimension( d );

		// create ImgLib2 Array
		final net.imglib2.img.basictypeaccess.ByteAccess access = new net.imglib2.img.basictypeaccess.array.ByteArray( data );
		final ArrayImg< net.imglib2.type.numeric.integer.ByteType, net.imglib2.img.basictypeaccess.ByteAccess> arrayImgLib2 =
			new ArrayImg< net.imglib2.type.numeric.integer.ByteType, net.imglib2.img.basictypeaccess.ByteAccess>( access, dim, new Fraction() );

		// create a Type that is linked to the container
		final net.imglib2.type.numeric.integer.ByteType linkedType = new net.imglib2.type.numeric.integer.ByteType( arrayImgLib2 );

		// pass it to the DirectAccessContainer
		arrayImgLib2.setLinkedType( linkedType );

		return arrayImgLib2;
	}

	/**
	 * wrapArrays an ImgLib1 {@link Image} of UnsignedByteType (has to be array) into an ImgLib2 {@link Img} using an {@link ArrayImg}
	 *
	 * @param image - input image
	 * @return
	 */
	public static Img< net.imglib2.type.numeric.integer.UnsignedByteType > wrapArrayUnsignedByteToImgLib2 ( final Image< UnsignedByteType > image )
	{
		// extract Byte[] array
		@SuppressWarnings( "unchecked" )
		final Array< UnsignedByteType, ByteAccess> array = (Array< UnsignedByteType, ByteAccess> ) image.getContainer();
		final ByteArray f = (ByteArray)array.update( null );
		final byte[] data = f.getCurrentStorageArray();

		// convert coordinates
		final long dim[] = new long[ image.getNumDimensions() ];
		for ( int d = 0; d < dim.length; ++d )
			dim[ d ] = image.getDimension( d );

		// create ImgLib2 Array
		final net.imglib2.img.basictypeaccess.ByteAccess access = new net.imglib2.img.basictypeaccess.array.ByteArray( data );
		final ArrayImg< net.imglib2.type.numeric.integer.UnsignedByteType, net.imglib2.img.basictypeaccess.ByteAccess> arrayImgLib2 =
			new ArrayImg< net.imglib2.type.numeric.integer.UnsignedByteType, net.imglib2.img.basictypeaccess.ByteAccess>( access, dim, new Fraction() );

		// create a Type that is linked to the container
		final net.imglib2.type.numeric.integer.UnsignedByteType linkedType = new net.imglib2.type.numeric.integer.UnsignedByteType( arrayImgLib2 );

		// pass it to the DirectAccessContainer
		arrayImgLib2.setLinkedType( linkedType );

		return arrayImgLib2;
	}

	public static void main( final String[] args )
	{
		ContainerFactory t;

		t = new CellContainerFactory( 5 );
		t = new ArrayContainerFactory();

		final ImageFactory< FloatType > f = new ImageFactory<FloatType>( new FloatType(), t );

		new ImageJ();
		final Image<FloatType> img = f.createImage( new int[]{ 19, 8, 3 } );

		int i = 0;

		for ( final FloatType ft : img )
			ft.set( i++ );

		ImageJFunctions.show( img );

		net.imglib2.img.display.imagej.ImageJFunctions.show( wrapFloatToImgLib2( img ) );
	}
}
