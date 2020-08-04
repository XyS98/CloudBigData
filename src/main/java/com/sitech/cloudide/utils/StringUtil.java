package com.sitech.cloudide.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sitech.cloudide.ssh.BaseConstant;

/**
 * 
 * <p>Title: StringUtil</p>
 * <p>Description: String util工具类</p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: 思特奇 </p>
 * @author heweia
 * @version 1.0
 * @createtime 2019-4-9 上午9:13:46
 *
 */
public class StringUtil {

    /**
     * 
     * @Title: containsIgnoreCase
     * @Description: 
     * @param originalStr
     * @param targetStr
     * @return
     * @return boolean
     * @author heweia
     * @version 1.0
     * @createtime 2019-4-9 上午9:14:34
     */
    public static boolean containsIgnoreCase( final String originalStr, final CharSequence targetStr ) {

        if ( null == originalStr ) {
            return false;
        }

        String originalStrCaps = originalStr.toUpperCase();
        String targetStrCaps = targetStr.toString().toUpperCase();
        return originalStrCaps.contains( targetStrCaps );
    }


    /**
     * Description: Remove {_, -, @, $, #, /, &} in string and make letter after
     * this uppercase.<br>
     * e.g. ni_lea-der@gmail./com -> niLeaDerGmail.Com
     * 
     * @param @param inputString
     * @param @param firstCharacterUppercase The first letter is need uppercase.
     * @return String
     * @throws
     */
    public static String convertToCamelCaseString( String inputString, boolean firstCharacterUppercase ) {
        if ( null == inputString ) {
            return null;
        }
        StringBuilder sb = new StringBuilder();

        boolean nextUpperCase = false;
        for ( int i = 0; i < inputString.length(); i++ ) {
            char c = inputString.charAt( i );

            switch ( c ) {
            case '_':
            case '-':
            case '@':
            case '$':
            case '#':
            case ' ':
            case '/':
            case '&':
                if ( sb.length() > 0 ) {
                    nextUpperCase = true;
                }
                break;

            default:
                if ( nextUpperCase ) {
                    sb.append( Character.toUpperCase( c ) );
                    nextUpperCase = false;
                } else {
                    sb.append( c );
                }
                break;
            }
        }

        if ( firstCharacterUppercase ) {
            sb.setCharAt( 0, Character.toUpperCase( sb.charAt( 0 ) ) );
        } else {
            sb.setCharAt( 0, Character.toLowerCase( sb.charAt( 0 ) ) );
        }

        return sb.toString();
    }

    /**
     * 
     * @Title: defaultIfBlank
     * @Description:  
     * @param originalStr
     * @param defaultStr
     * @return
     * @return String
     * @author heweia
     * @version 1.0
     */
    public static String defaultIfBlank( String originalStr, String defaultStr ) {
        if ( StringUtil.isBlank( originalStr ) ) {
            return defaultStr;
        }
        Collections.emptyList();
        return originalStr;
    }

    /**
     * 
     * @Title: equalsIgnoreCaseAll
     * @Description:  
     * @param targetStr
     * @param compareStrArray
     * @return
     * @return boolean
     * @author heweia
     * @version 1.0
     */
    public static boolean equalsIgnoreCaseAll( String targetStr, String... compareStrArray ) {

        if ( StringUtil.isBlank( targetStr ) || null == compareStrArray || 0 == compareStrArray.length ) {
            return false;
        }
        for ( int i = 0; i < compareStrArray.length; i++ ) {
            if ( !targetStr.equalsIgnoreCase( compareStrArray[i] ) ) {
                return false;
            }
        }
        return true;
    }

    /**
     * 
     * @Title: equalsIgnoreCaseOne
     * @Description:  
     * @param targetStr
     * @param compareStrArray
     * @return
     * @return boolean
     * @author heweia
     * @version 1.0
     */
    public static boolean equalsIgnoreCaseOne( String targetStr, String... compareStrArray ) {

        if ( StringUtil.isBlank( targetStr ) || null == compareStrArray || 0 == compareStrArray.length ) {
            return false;
        }
        for ( int i = 0; i < compareStrArray.length; i++ ) {
            if ( targetStr.equalsIgnoreCase( compareStrArray[i] ) ) {
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * @Title: findAllByRegex
     * @Description: 
     * @param originalStr
     * @param regex
     * @return
     * @return List<String>
     * @author heweia
     * @version 1.0
     */
    public static List< String > findAllByRegex( String originalStr, String regex ) {

        if ( StringUtil.isBlank( originalStr ) || StringUtil.isBlank( regex ) )
            return null;

        List< String > targetStrList = new ArrayList< String >();
        final Pattern patternOfTargetStr = Pattern.compile( regex, Pattern.CANON_EQ );
        final Matcher matcherOfTargetStr = patternOfTargetStr.matcher( originalStr );
        while ( matcherOfTargetStr.find() ) {
            targetStrList.add( StringUtil.trimToEmpty( matcherOfTargetStr.group() ) );
        }
        return targetStrList;
    }

    /**
     * 
     * @Title: findFirstByRegex
     * @Description: 
     * @param originalStr
     * @param regex
     * @return
     * @return String
     * @author heweia
     * @version 1.0
     */
    public static String findFirstByRegex( String originalStr, String regex ) {

        if ( StringUtil.isBlank( originalStr ) || StringUtil.isBlank( regex ) )
            return "";

        final Pattern patternOfTargetStr = Pattern.compile( regex, Pattern.CANON_EQ );
        final Matcher matcherOfTargetStr = patternOfTargetStr.matcher( originalStr );
        if ( matcherOfTargetStr.find() ) {
            return StringUtil.trimToEmpty( matcherOfTargetStr.group() );
        }
        return "";
    }

    public static String generateLineBlank( int lines ) {
        StringBuilder sb = new StringBuilder();

        for ( int i = 0; i < lines; i++ ) {
            sb.append( "\n" );
        }

        return sb.toString();
    }

    /**
     * make first letter lower case for str
     * 
     * @return Same letter, but the first letter is lower case.
     */
    public static String makeFirstLetterLowerCase( String str ) {
        String firstLetter = str.substring( 0, 1 );
        return firstLetter.toLowerCase() + str.substring( 1, str.length() );
    }

    /***
     * check if orginalStr is null or empty. <br>
     * If have more than one originalStr, use isBlank(String...
     * originalStrArray)
     * 
     * @param originalStr
     * @return true or false;
     */
    public static boolean isBlank( String originalStr ) {
        if ( null == originalStr ) {
            return true;
        }
        if ( originalStr.contains( BaseConstant.WORD_SEPARATOR ) ) {
            return false;
        }
        return trimToEmpty( originalStr ).isEmpty();
    }

    /***
     * check if orginalStr is null or empty
     * 
     * @param String
     *            ... originalStrArray
     * @return true if have one blank at least.
     */
    public static boolean isBlank( String... originalStrArray ) {

        if ( null == originalStrArray || 0 == originalStrArray.length )
            return true;
        for ( int i = 0; i < originalStrArray.length; i++ ) {
            if ( isBlank( originalStrArray[i] ) )
                return true;
        }
        return false;
    }

    /**
     * check the originalStr is contain the whitespace
     * 
     * @param originalStr
     * @return true if contain whitespace
     */
    public static boolean isContainWhitespace( String originalStr ) {

        if ( StringUtil.isBlank( originalStr ) ) {
            return true;
        }
        int strLen = originalStr.length();
        for ( int i = 0; i < strLen; i++ ) {
            char ch = originalStr.charAt( i );
            if ( Character.isWhitespace( ch ) ) {
                return true;
            }
        }
        return false;
    }

    /**
     * 字符串连接，使用指定分隔符
     * 
     * @param subStr
     * @return
     */
    public static String join( String... subStrs ) {

        if ( null == subStrs || 0 == subStrs.length ) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for ( String subStr : subStrs ) {
            sb.append( subStr ).append( BaseConstant.WORD_SEPARATOR );
        }
        String sbStr = sb.toString();
        if ( sbStr.endsWith( BaseConstant.WORD_SEPARATOR ) ) {
            sbStr = StringUtil.replaceLast( sbStr, BaseConstant.WORD_SEPARATOR, "" );
        }
        return sbStr;
    }

    /**
     * Description: Replaces last substring of this string that matches the
     * given regular expression with the given replacement.<br>
     * Do not worry about null pointer
     * 
     * @param @param regex
     * @param @param replacement
     * @return String
     * @throws
     */
    public static String replaceAll( String originalStr, String replacement, String regex ) {
        return StringUtil.trimToEmpty( originalStr ).replaceAll( regex, replacement );
    }

    public static String replaceAll( String originalStr, String replacement, String... regexArray ) {

        if ( 0 == regexArray.length )
            return originalStr;

        for ( String regex : regexArray ) {
            originalStr = StringUtil.replaceAll( originalStr, replacement, regex );
        }

        return originalStr;
    }

    /**
     * Description: Replaces last substring of this string that matches the
     * given regular expression with the given replacement.
     * 
     * @param @param regex
     * @param @param replacement
     * @param @return
     * @return String
     * @throws
     */
    public static String replaceLast( String originalStr, String regex, String replacement ) {

        if ( StringUtil.isBlank( originalStr ) )
            return "";

        int index = originalStr.lastIndexOf( regex );
        if ( -1 == index )
            return originalStr;

        String temp = originalStr.substring( 0, index );
        String temp2 = originalStr.substring( index, originalStr.length() );

        temp2 = temp2.replaceFirst( regex, replacement );

        originalStr = temp + temp2;

        return originalStr;
    }

    
    /**
     * Description: Replaces all {n} placeholder use params
     * 
     * @param originalStr
     *            a string such as :
     *            "select * from table where id={0}, name={1}, gender={3}"
     * @param replacementParams
     *            real params: 1,yinshi.nc,male
     * @note n start with 0
     */
    public static String replaceSequenced( String originalStr, Object... replacementParams ) {

        if ( StringUtil.isBlank( originalStr ) )
            return "";
        if ( null == replacementParams || 0 == replacementParams.length )
            return originalStr;

        for ( int i = 0; i < replacementParams.length; i++ ) {
            String elementOfParams = replacementParams[i] + "";
            if ( StringUtil.trimToEmpty( elementOfParams ).equalsIgnoreCase( "null" ) )
                elementOfParams = "";
            originalStr = originalStr.replace( "{" + i + "}", StringUtil.trimToEmpty( elementOfParams ) );
        }

        return originalStr;
    }

    public static String setPrefix( String originalStr, String prefix ) {
        originalStr = StringUtil.trimToEmpty( originalStr );
        prefix = StringUtil.trimToEmpty( prefix );
        if ( !originalStr.startsWith( prefix ) ) {
            originalStr = prefix + originalStr;
        }
        return originalStr;
    }

    /**
     * 
     * @Title: subStringIfTooLong
     * @Description: TODO字符串截取
     * @param originalStr
     * @param maxLength
     * @param suffix
     * @return
     * @return String
     * @author heweia
     * @version 1.0
     */
    public static String subStringIfTooLong( String originalStr, int maxLength, String suffix ) {
        if ( StringUtil.isBlank( originalStr ) )
            return "";
        if ( maxLength < 0 )
            maxLength = 0;
        if ( originalStr.length() > maxLength )
            return originalStr.substring( 0, maxLength ) + StringUtil.trimToEmpty( suffix );
        return originalStr;
    }

    /**
     * Returns a copy of the string, with leading and trailing whitespace
     * omitted. Don't worry the NullPointerException. Will never return Null.
     * 
     * @param originalStr
     * @return "" or String without empty str.
     */
    public static String trimToEmpty( String originalStr ) {
        if ( null == originalStr || originalStr.isEmpty() )
            return "";
        if ( originalStr.equals( BaseConstant.WORD_SEPARATOR ) )
            return originalStr;
        return originalStr.trim();
    }

    /**
     * URL编码
     * 
     * @param s
     *            String to be translated.
     * @param enc
     *            The name of a supported character encoding.
     * @return
     */
    public static String urlEncode( String s, String enc ) {
        if ( StringUtil.isBlank( s ) )
            return StringUtil.trimToEmpty( s );
        try {
            return java.net.URLEncoder.encode( trimToEmpty( s ), enc );
        } catch ( UnsupportedEncodingException e ) {
            return s;
        }
    }

    /**
     * URL编码,使用UTF-8编码
     * 
     * @param s
     *            String to be translated.
     * @param enc
     *            The name of a supported character encoding.
     * @return
     */
    public static String urlEncode( String s ) {
        if ( StringUtil.isBlank( s ) )
            return StringUtil.trimToEmpty( s );
        return urlEncode( trimToEmpty( s ), "UTF-8" );
    }

}
