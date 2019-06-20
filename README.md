# Digit-Recognition
Handwritten Digit Recognition with opencv and deeplearning4j
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
	<META HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=utf-8">
	<TITLE></TITLE>
	<META NAME="GENERATOR" CONTENT="LibreOffice 4.1.6.2 (Linux)">
	<META NAME="AUTHOR" CONTENT="sangeeta">
	<META NAME="CREATED" CONTENT="20190217;120900000000000">
	<META NAME="CHANGED" CONTENT="20190620;85625000000000">
	<META NAME="AppVersion" CONTENT="12.0000">
	<META NAME="DocSecurity" CONTENT="0">
	<META NAME="HyperlinksChanged" CONTENT="false">
	<META NAME="LinksUpToDate" CONTENT="false">
	<META NAME="ScaleCrop" CONTENT="false">
	<META NAME="ShareDoc" CONTENT="false">
	<STYLE TYPE="text/css">
	<!--
		@page { size: 8.5in 11in; margin: 1in }
		P { margin-bottom: 0.1in; direction: ltr; color: #00000a; line-height: 115%; text-align: left; widows: 2; orphans: 2 }
		P.western { font-family: "Calibri", serif; font-size: 11pt; so-language: en-US }
		P.cjk { font-family: ; font-size: 11pt; so-language: en-US }
		P.ctl { font-family: ; font-size: 11pt; so-language: ar-SA }
		A:link { color: #0000ff; so-language: zxx }
	-->
	</STYLE>
</HEAD>
<BODY LANG="en-US" TEXT="#00000a" LINK="#0000ff" DIR="LTR">
<P CLASS="western" STYLE="margin-bottom: 0.14in"><FONT FACE="Times New Roman, serif"><FONT SIZE=4><B>Architecture:</B></FONT></FONT></P>
<P CLASS="western" ALIGN=CENTER STYLE="margin-bottom: 0.14in"><IMG SRC="images/REPORT-0_html_e0cc22e9.png" NAME="Picture 19" ALIGN=BOTTOM HSPACE=1 WIDTH=403 HEIGHT=387 BORDER=0></P>
<P CLASS="western" ALIGN=CENTER STYLE="margin-bottom: 0.14in"><IMG SRC="images/REPORT-0_html_e535a8a7.png" NAME="Picture 16" ALIGN=BOTTOM HSPACE=1 WIDTH=254 HEIGHT=459 BORDER=0>
</P>
<P CLASS="western" ALIGN=CENTER STYLE="margin-bottom: 0.14in"><FONT FACE="TimesNewRoman, serif"><FONT SIZE=3><B>Fig.
Framework of Handwritten Character Recognition System</B></FONT></FONT></P>
<P CLASS="western" ALIGN=CENTER STYLE="margin-bottom: 0.14in"><BR><BR>
</P>
<P CLASS="western" STYLE="margin-bottom: 0.14in"><BR><BR>
</P>
<P CLASS="western" STYLE="margin-bottom: 0.14in"><BR><BR>
</P>
<P CLASS="western" STYLE="margin-bottom: 0.14in"><BR><BR>
</P>
<P CLASS="western" STYLE="margin-bottom: 0.14in"><BR><BR>
</P>
<P CLASS="western" STYLE="margin-bottom: 0.14in"><BR><BR>
</P>
<P CLASS="western" STYLE="margin-bottom: 0.14in"><BR><BR>
</P>
<P CLASS="western" STYLE="margin-bottom: 0.14in"><BR><BR>
</P>
<P CLASS="western" STYLE="margin-bottom: 0.14in"><BR><BR>
</P>
<P CLASS="western" STYLE="margin-bottom: 0.14in"><FONT FACE="Times New Roman, serif"><FONT SIZE=4><B>Modules:</B></FONT></FONT></P>
<P CLASS="western" ALIGN=JUSTIFY STYLE="margin-bottom: 0in; line-height: 100%">
<FONT FACE="Times New Roman, serif"><FONT SIZE=3><B>1. Preprocessing</B></FONT></FONT></P>
<P CLASS="western" ALIGN=JUSTIFY STYLE="margin-bottom: 0in; line-height: 100%">
<BR>
</P>
<P CLASS="western" ALIGN=JUSTIFY STYLE="margin-bottom: 0in"><FONT FACE="Times New Roman, serif"><FONT SIZE=3>The
Preprocessing is performed on acquired input data. It enhances the
quality of input data and makes it more suitable for the next phase
of recognition system. Gray scale conversion, binary conversion,
noise removal, etc. are various techniques that are performed in this
phase.</FONT></FONT></P>
<P CLASS="western" ALIGN=JUSTIFY STYLE="margin-bottom: 0in"><BR>
</P>
<P CLASS="western" ALIGN=CENTER STYLE="margin-bottom: 0in"><IMG SRC="REPORT-0_html_e18ae6ac.png" NAME="Picture 1" ALIGN=BOTTOM HSPACE=1 WIDTH=450 HEIGHT=170 BORDER=0></P>
<P CLASS="western" ALIGN=JUSTIFY STYLE="margin-bottom: 0in"><BR>
</P>
<P CLASS="western" ALIGN=JUSTIFY STYLE="margin-bottom: 0in"><FONT FACE="Times New Roman, serif"><FONT SIZE=3><B>2.
Segmentation</B></FONT></FONT></P>
<P CLASS="western" ALIGN=JUSTIFY STYLE="margin-bottom: 0in"><BR>
</P>
<P CLASS="western" ALIGN=JUSTIFY STYLE="margin-bottom: 0in"><FONT FACE="Times New Roman, serif"><FONT SIZE=3>Segmentation,
is the process of splitting input text data image to line and then
after individual character. It removes the unwanted part from the
data image. There are two types of segmentation available, External
and Internal. External segmentation is segmenting the paragraphs,
lines and words. On the other side internal segmentation is
segmenting of individual character from input text data. Various
algorithms are available for segmentation. Histogram profiles and
connected component analysis are some of the methods for line
segmentation. Spatial space detection for the words and Histogram
method for the characters and other symbols which are used in. For
character segmentation authors are using bounding box technique.
After successful segmentation, resize operation is performed on all
segmented image for uniform size.</FONT></FONT></P>
<P CLASS="western" ALIGN=CENTER STYLE="margin-bottom: 0in"><IMG SRC="REPORT-0_html_3a85874e.png" NAME="Picture 4" ALIGN=BOTTOM HSPACE=1 WIDTH=413 HEIGHT=81 BORDER=0></P>
<P CLASS="western" ALIGN=JUSTIFY STYLE="margin-bottom: 0in"><BR>
</P>
<P CLASS="western" ALIGN=CENTER STYLE="margin-bottom: 0in"><FONT FACE="TimesNewRoman, serif"><FONT SIZE=3><B>Fig.
Segmented Line based on Histogram</B></FONT></FONT></P>
<P CLASS="western" ALIGN=CENTER STYLE="margin-bottom: 0in"><IMG SRC="REPORT-0_html_64e33df6.png" NAME="Picture 7" ALIGN=BOTTOM HSPACE=1 WIDTH=390 HEIGHT=108 BORDER=0></P>
<P CLASS="western" ALIGN=CENTER STYLE="margin-bottom: 0in"><FONT FACE="TimesNewRoman, serif"><FONT SIZE=3><B>Fig.
Segmented Characters based on Histogram</B></FONT></FONT></P>
<P CLASS="western" ALIGN=JUSTIFY STYLE="margin-bottom: 0in"><FONT FACE="Times New Roman, serif"><FONT SIZE=3><B>3.
Feature Extraction</B></FONT></FONT></P>
<P CLASS="western" ALIGN=JUSTIFY STYLE="margin-bottom: 0in"><BR>
</P>
<P CLASS="western" ALIGN=JUSTIFY STYLE="margin-bottom: 0in"><FONT FACE="Times New Roman, serif"><FONT SIZE=3>Feature
Extraction is the process of collecting different and very useful
information of an object or a group of objects, so based on that
collected information, we can classify new unknown objects by
matching it. Feature is the robust representation of the raw data.</FONT></FONT></P>
<P CLASS="western" ALIGN=CENTER STYLE="margin-bottom: 0in"><IMG SRC="REPORT-0_html_c01a2c3e.png" NAME="Picture 10" ALIGN=BOTTOM HSPACE=1 WIDTH=415 HEIGHT=257 BORDER=0></P>
<P CLASS="western" ALIGN=CENTER STYLE="margin-bottom: 0in"><FONT FACE="TimesNewRoman, serif"><FONT SIZE=3><B>Fig.
Feature Extraction Techniques</B></FONT></FONT></P>
<P CLASS="western" ALIGN=JUSTIFY STYLE="margin-bottom: 0in"><BR>
</P>
<P CLASS="western" ALIGN=JUSTIFY STYLE="margin-bottom: 0in"><FONT FACE="Times New Roman, serif"><FONT SIZE=3><B>4.
Classification</B></FONT></FONT></P>
<P CLASS="western" ALIGN=JUSTIFY STYLE="margin-bottom: 0in"><BR>
</P>
<P CLASS="western" ALIGN=JUSTIFY STYLE="margin-bottom: 0in"><FONT FACE="Times New Roman, serif"><FONT SIZE=3>Classification
or Recognition process is for decision making, like this new
character fit in which class or looks like. It means, in the phase of
classification characters are identified and assign labeling.
Performance of the classification depends on good feature extraction
and selection. Various classification techniques are available and
they all are ultimately based on image processing and artificial
intelligence.</FONT></FONT></P>
<P CLASS="western" STYLE="margin-bottom: 0.14in"><BR><BR>
</P>
<P CLASS="western" ALIGN=CENTER STYLE="margin-bottom: 0.14in"><IMG SRC="REPORT-0_html_a602d1e1.png" NAME="Picture 13" ALIGN=BOTTOM HSPACE=1 WIDTH=415 HEIGHT=286 BORDER=0></P>
<P CLASS="western" ALIGN=CENTER STYLE="margin-bottom: 0.14in"><FONT FACE="TimesNewRoman, serif"><FONT SIZE=3><B>Fig.
Classification Techniques</B></FONT></FONT></P>
<P CLASS="western" STYLE="margin-bottom: 0.14in"><BR><BR>
</P>
<P CLASS="western" STYLE="margin-bottom: 0.14in"><BR><BR>
</P>
<P CLASS="western" STYLE="margin-bottom: 0.14in"><BR><BR>
</P>
<P CLASS="western" STYLE="margin-bottom: 0.14in"><BR><BR>
</P>
<P CLASS="western" STYLE="margin-bottom: 0.14in"><BR><BR>
</P>
<P CLASS="western" STYLE="margin-bottom: 0.14in"><BR><BR>
</P>
<P CLASS="western" STYLE="margin-bottom: 0.14in"><BR><BR>
</P>
<P CLASS="western" STYLE="margin-bottom: 0.14in"><BR><BR>
</P>
<P CLASS="western" STYLE="margin-bottom: 0.14in"><BR><BR>
</P>
<P CLASS="western" STYLE="margin-bottom: 0.14in"><BR><BR>
</P>
<P CLASS="western" STYLE="margin-bottom: 0.14in"><BR><BR>
</P>
<P CLASS="western" STYLE="margin-bottom: 0.14in"><BR><BR>
</P>
<P CLASS="western" STYLE="margin-bottom: 0.14in"><BR><BR>
</P>
<P CLASS="western" STYLE="margin-bottom: 0.14in"><BR><BR>
</P>
<P CLASS="western" STYLE="margin-bottom: 0.14in"><FONT FACE="Times New Roman, serif"><FONT SIZE=4><B>Algorithm:</B></FONT></FONT></P>
<P CLASS="western" ALIGN=JUSTIFY STYLE="margin-left: -0.02in; margin-top: 0.31in; margin-bottom: 0in; background: #ffffff">
<FONT FACE="Times New Roman, serif"><FONT SIZE=3><B>Nearest Neighbor:</B></FONT></FONT></P>
<P CLASS="western" ALIGN=JUSTIFY STYLE="margin-top: 0.06in; margin-bottom: 0in; background: #ffffff">
<FONT FACE="Times New Roman, serif"><FONT SIZE=3>The
k-nearest-neighbors algorithm is a classification algorithm, and it
is supervised: it takes a bunch of labelled points and uses them to
learn how to label other points. To label a new point, it looks at
the labelled points closest to that new point (those are its nearest
neighbors), and has those neighbors vote, so whichever label the most
of the neighbors have is the label for the new point (the “k” is
the number of neighbors it checks).</FONT></FONT></P>
<P CLASS="western" STYLE="margin-bottom: 0.14in"><BR><BR>
</P>
<P CLASS="western" ALIGN=JUSTIFY STYLE="margin-bottom: 0.14in"><IMG SRC="REPORT-0_html_534eadb8.png" NAME="Image1" ALIGN=BOTTOM HSPACE=1 WIDTH=531 HEIGHT=380 BORDER=0></P>
</BODY>
</HTML>