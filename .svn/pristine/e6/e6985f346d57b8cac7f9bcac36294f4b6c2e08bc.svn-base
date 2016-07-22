/*
 * name: auto
 * version: 0.1.0
 * Copyright (c) 2015 - 2016
 */
const gulp = require('gulp');
const less = require('gulp-less');
const autoprefixer = require('gulp-autoprefixer');
const cssmin = require('gulp-clean-css');
const uglify = require('gulp-uglify');
const imagemin = require('gulp-imagemin');
const cache = require('gulp-cache');

const paths = {
    less: './resources-src/less/**/*.less',
    js: './resources-src/js/**/*.js',
    images: './resources-src/images/**/*',
    vendor: ['./resources-src/vendor/**/*', '!./resources-src/vendor/lib/*'],
    lib: './resources-src/vendor/lib/*'
};

gulp.task('less', () => {
    return gulp.src(paths.less)
        .pipe(less())
        .pipe(cssmin({compatibility: 'ie8'}))
        .pipe(autoprefixer({
            browsers: ['> 5%'],
            cascade: false
        }))
        .pipe(gulp.dest('./resources/css/'))
});
gulp.task('js', () => {
    return gulp.src(paths.js)
        //.pipe(babel({
        //    presets: ['es2015']
        //}))
        .pipe(uglify())
        .pipe(gulp.dest('./resources/js/'))
});
gulp.task('images', () => {
    return gulp.src(paths.images)
        .pipe(cache(imagemin()))
        .pipe(gulp.dest('./resources/images/'))
});
gulp.task('vendor', () => {
    return gulp.src(paths.vendor)
        .pipe(gulp.dest('./resources/vendor/'))
});
gulp.task('vendor-lib', () => {
    return gulp.src(paths.lib)
        .pipe(uglify())
        .pipe(gulp.dest('./resources/vendor/lib/'))
});
gulp.task('watch', () => {
    gulp.watch(paths.less, ['less']);
    gulp.watch(paths.js, ['js']);
    gulp.watch(paths.images, ['images']);
    gulp.watch(paths.vendor, ['vendor']);
    gulp.watch(paths.lib, ['vendor-lib']);
});
gulp.task('default', ['less', 'js', 'images', 'vendor', 'vendor-lib', 'watch']);
